
document.getElementById('authForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    const isLogin = document.getElementById('formTitle').textContent === 'Iniciar Sesión';
    
    const url = isLogin ? '/login' : 'login/new';
    const method = 'POST';

    fetch(url, {
        method: method,
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: `username=${encodeURIComponent(username)}&password=${encodeURIComponent(password)}`
    })
    .then(response => {
        if (response.ok) {
            alert(isLogin ? 'Inicio de sesión exitoso' : 'Registro exitoso');
            if (isLogin) {
                window.location.href = "../properties.html";
            }
        } else {
            alert(isLogin ? 'Usuario o contraseña incorrectos' : 'Error en el registro');
        }
    })
    .catch(error => {
        console.error(isLogin ? 'Error en el inicio de sesión:' : 'Error en el registro:', error);
    });
});

document.getElementById('toggleLink').addEventListener('click', function() {
    const formTitle = document.getElementById('formTitle');
    const submitButton = document.getElementById('submitButton');
    const toggleLink = document.getElementById('toggleLink');
    const isLogin = formTitle.textContent === 'Iniciar Sesión';

    if (isLogin) {
        formTitle.textContent = 'Registrar';
        submitButton.textContent = 'Registrar';
        toggleLink.textContent = '¿Ya tienes cuenta? Inicia sesión aquí.';
    } else {
        formTitle.textContent = 'Iniciar Sesión';
        submitButton.textContent = 'Entrar';
        toggleLink.textContent = '¿No tienes cuenta? Regístrate aquí.';
    }
});
