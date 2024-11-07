// Función para mostrar mensajes
function showMessage(message, type) {
    const messageBox = document.getElementById('messageBox');
    messageBox.textContent = message;
    messageBox.className = `alert alert-${type}`; // Puede ser 'alert-success' o 'alert-danger'
    messageBox.style.display = 'block';

    // Ocultar el mensaje después de unos segundos
    setTimeout(() => {
        messageBox.style.display = 'none';
    }, 3000);
}

// Configuración de la API
const API_BASE_URL = 'http://localhost:8080/api';

// Función para crear un nuevo post
async function createPost(event) {
    event.preventDefault();

    // Obtener los datos del formulario
    const username = document.getElementById('username').value;
    const streamTitle = document.getElementById('streamTitle').value;
    const content = document.getElementById('content').value;

    // Validación
    if (!username || !streamTitle || !content) {
        showMessage('Todos los campos son obligatorios', 'danger');
        return;
    }

    // Crear objeto con la petición
    const postRequest = { username, content, streamTitle };

    try {
        // Enviar petición POST
        const response = await fetch(`${API_BASE_URL}/posts`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            },
            body: JSON.stringify(postRequest)
        });

        if (response.ok) {
            // Si se crea correctamente, actualizar la lista de posts
            await getPosts();
            document.getElementById('postForm').reset();
            showMessage('Post creado exitosamente', 'success');
        } else {
            showMessage('Error al crear el post. Inténtalo de nuevo.', 'danger');
        }
    } catch (error) {
        console.error('Error:', error);
        showMessage('Error al conectar con el servidor. Inténtalo de nuevo.', 'danger');
    }
}

// Función para obtener todos los posts
async function getPosts() {
    try {
        const response = await fetch(`${API_BASE_URL}/posts`, {
            method: 'GET',
            headers: {
                'Accept': 'application/json'
            }
        });

        if (response.ok) {
            const posts = await response.json();
            displayPosts(posts);
        } else {
            console.error('Error al obtener posts.');
        }
    } catch (error) {
        console.error('Error:', error);
        showMessage('Error al obtener los posts. Verifica tu conexión.', 'danger');
    }
}

// Función para mostrar los posts en la página
function displayPosts(posts) {
    const postsList = document.getElementById('postsList');
    postsList.innerHTML = '';

    posts.forEach(post => {
        const li = document.createElement('li');
        li.innerHTML = `<strong>${post.username}</strong> en ${post.streamTitle}:<br>${post.content}`;
        postsList.appendChild(li);
    });
}

// Agregar event listeners cuando se carga la página
document.addEventListener('DOMContentLoaded', () => {
    document.getElementById('postForm').addEventListener('submit', createPost);
    getPosts(); // Cargar posts existentes
});
