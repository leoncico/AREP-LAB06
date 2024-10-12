

function openAddProperty() {
    document.getElementById('propertyForm').reset();
    const form = document.getElementById('propertyForm');
    form.style.display = 'block';
    form.onsubmit = addProperty;
}

document.getElementById('addButton').addEventListener('click', openAddProperty);
async function addProperty(event) {
    event.preventDefault(); 
    const address = document.getElementById('address').value;
    const price = parseFloat(document.getElementById('price').value);
    const size = parseFloat(document.getElementById('size').value);
    const description = document.getElementById('description').value;

    const property = {
        address: address,
        price: price,
        size: size,
        description: description
    };

    try {
        const response = await fetch('/properties/create', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(property)
        });

        if (response.ok) {
            document.getElementById('propertyForm').reset();
            document.getElementById('propertyForm').style.display = 'none';
            fetchProperties();
        } else {
            console.error('Error al crear la propiedad:', response.statusText);
        }
    } catch (error) {
        console.error('Error al realizar la petición:', error);
    }
}



let currentPage = 1;
const propertiesPerPage = 5;

async function fetchProperties() {
    try {
        const response = await fetch('/properties');
        if (response.ok) {
            const properties = await response.json();
            displayProperties(properties, currentPage);
            setupPagination(properties.length);
        } else {
            console.error('Error al obtener propiedades:', response.statusText);
        }
    } catch (error) {
        console.error('Error al realizar la petición:', error);
    }
}

function displayProperties(properties, page) {
    const propertyList = document.querySelector('#propertyList tbody');
    propertyList.innerHTML = '';
    const startIndex = (page - 1) * propertiesPerPage;
    const endIndex = startIndex + propertiesPerPage;
    const paginatedProperties = properties.slice(startIndex, endIndex);
    paginatedProperties.forEach(property => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${property.id}</td>
            <td>${property.address}</td>
            <td>$${property.price.toFixed(2)}</td>
            <td>${property.size} m²</td>
            <td>${property.description}</td>
            <td>
                <button onclick="editProperty(${property.id})">Editar</button>
                <button onclick="deleteProperty(${property.id})">Eliminar</button>
            </td>
        `;
        propertyList.appendChild(row);
    });
}

function setupPagination(totalProperties) {
    const paginationDiv = document.getElementById('pagination');
    paginationDiv.innerHTML = '';
    const totalPages = Math.ceil(totalProperties / propertiesPerPage);

    for (let i = 1; i <= totalPages; i++) {
        const button = document.createElement('button');
        button.innerText = i;
        button.classList.add('pagination-btn');
        button.addEventListener('click', function() {
            currentPage = i;
            fetchProperties();
        });
        paginationDiv.appendChild(button);
    }
}

function editProperty(id) {
    fetch(`/properties/${id}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Error al obtener la propiedad');
            }
            return response.json();
        })
        .then(property => {
            if (!property) {
                throw new Error('No se encontró la propiedad');
            }
            document.getElementById('address').value = property.address;
            document.getElementById('price').value = property.price;
            document.getElementById('size').value = property.size;
            document.getElementById('description').value = property.description;
            document.getElementById('propertyForm').style.display = 'block';
            document.getElementById('propertyForm').onsubmit = (event) => updateProperty(event, id);
        })
        .catch(error => {
            console.error('Error al editar la propiedad:', error);
        });
}


async function updateProperty(event, id) {
    event.preventDefault();
    
    const property = {
        address: document.getElementById('address').value,
        price: parseFloat(document.getElementById('price').value),
        size: parseFloat(document.getElementById('size').value),
        description: document.getElementById('description').value
    };

    try {
        const response = await fetch(`/properties/${id}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(property)
        });

        if (response.ok) {
            document.getElementById('propertyForm').reset();
            document.getElementById('propertyForm').style.display = 'none';
            fetchProperties();
        } else {
            console.error('Error al actualizar la propiedad:', response.statusText);
        }
    } catch (error) {
        console.error('Error en la petición:', error);
    }
}

async function deleteProperty(id) {
    try {
        const response = await fetch(`/properties/${id}`, {
            method: 'DELETE'
        });

        if (response.ok) {
            fetchProperties(); 
        } else {
            console.error('Error al eliminar la propiedad:', response.statusText);
        }
    } catch (error) {
        console.error('Error en la petición:', error);
    }
}

window.onload = () => {
    currentPage = 1;
    fetchProperties();
};