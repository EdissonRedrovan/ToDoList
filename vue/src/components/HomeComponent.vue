<template>
  <div class="home-container">
    <header class="header">
      <img src="@/assets/ToDoList1.png" alt="Logo" class="logo" />
    </header>
    <main class="main-content">
      <div class="section-left">
        <img src="@/assets/man-working.png" alt="Man Working" class="image" />
      </div>
      <div class="section-center">
        <font-awesome-icon icon="tasks" class="main-icon" />
        <h2>Bienvenido a tu Lista de Tareas</h2>
        <p>Organiza tu vida, una tarea a la vez.</p>
        <router-link to="/login">
          <button class="get-started-button">Get Started</button>
        </router-link>
        <div class="additional-info">
          <font-awesome-icon icon="check" /> Completa tus tareas
          <font-awesome-icon icon="trash" /> Elimina las que ya no necesites
        </div>
      </div>
      <div class="section-right">
        <img src="@/assets/woman-working.png" alt="Woman Working" class="image" />
      </div>
    </main>
    <footer class="footer">
      <p>© 2024 ToDoList. Todos los derechos reservados.</p>
      <p><a href="#">Términos de Uso</a> | <a href="#">Política de Privacidad</a></p>
    </footer>
  </div>
</template>

<script>
export default {
  name: 'HomeComponent',
};
</script>

<style scoped>
.home-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-between;
  height: 100vh;
  background-color: #f2f8f5;
  color: #2c3e50;
}

.header {
  width: 100%;
  padding: 20px;
  text-align: left;
}

.logo {
  height: 60px; /* Ajusta el tamaño del logo según sea necesario */
  width: 60px; /* Ajusta el tamaño del logo según sea necesario */
  border-radius: 50%; /* Hace el logo circular */
  margin-left: 40px; /* Añade margen izquierdo al logo */
}

.main-content {
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 80%;
  flex-grow: 1;
}

.section-left,
.section-right {
  flex: 1;
  text-align: center;
}

.image {
  max-width: 100%;
  max-height: 380px; /* Limita la altura máxima de las imágenes para igualarlas */
  width: auto;
  height: auto;
  padding: 20px;
}

.section-center {
  flex: 2;
  text-align: center;
}

.main-icon {
  font-size: 50px;
  color: #42b983;
}

h2 {
  margin: 20px 0;
}

.get-started-button {
  padding: 10px 20px;
  background-color: #42b983;
  color: #fff;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.get-started-button:hover {
  background-color: #35916e;
}

.additional-info {
  margin-top: 20px;
  font-size: 14px;
  color: #555;
}

.footer {
  background-color: #e0e0e0;
  width: 100%;
  padding: 10px;
  text-align: center;
  font-size: 14px;
}
</style>