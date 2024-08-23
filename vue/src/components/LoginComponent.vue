<!-- src/components/Login.vue -->
<template>
    <div class="login-container">
      <div class="login-box">
        <img src="@/assets/ToDoList1.png" alt="Logo" class="login-logo" />
        <h1>Login</h1>
        <form @submit.prevent="submitLogin">
          <div class="input-group">
            <input v-model="username" type="text" placeholder="Username" required />
          </div>
          <div class="input-group">
            <input v-model="password" type="password" placeholder="Password" required />
          </div>
          <button type="submit" class="login-button">Login</button>
        </form>
      </div>
    </div>
  </template>
  
  <script>
  import { mapActions } from 'vuex';  // Importa mapActions desde vuex

  export default {
    data() {
      return {
        username: '',
        password: '',
      };
    },
    methods: {
      ...mapActions(['login']),  // Mapea la acción login como un método
      async submitLogin() {
        if (this.username === 'admin' && this.password === 'admin') {
          await this.login();
          // Autenticación simulada
          console.log('redirecting to /dashboard');
          this.$router.push('/dashboard');
        } else {
          alert('Credenciales incorrectas');
        }
      },
    },
  };
  </script>
  
  <style scoped>
/* Centrando el contenedor del login en la pantalla */
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #f5f5f5;
  margin: 0;
  padding: 0;  /* Añadir padding 0 */
  box-sizing: border-box;  /* Asegurar que el box-sizing sea border-box */
}

/* Estilos de la caja de login */
.login-box {
  background: #fff;
  padding: 40px;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  text-align: center;
  width: 100%;
  max-width: 400px;
}

.login-logo {
  width: 80px; /* Tamaño del logo */
  height: 80px;
  border-radius: 50%; /* Puntas redondeadas */
  object-fit: cover;
  position: absolute;
  top: 40px; /* Posición del logo */
  left: 50%;
  transform: translateX(-50%);
  background-color: #fff; /* Fondo blanco para el logo */
  padding: 5px; /* Espaciado alrededor del logo */
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

h1 {
  margin-bottom: 20px;
  color: #2c3e50;
  font-size: 24px;
}

/* Estilos de los grupos de entrada */
.input-group {
  margin-bottom: 15px;
}

.input-group input {
  width: 100%;
  padding: 10px;
  font-size: 16px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}

/* Estilos del botón de login */
.login-button {
  width: 100%;
  padding: 10px;
  background-color: #42b983;
  color: #fff;
  font-size: 16px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.login-button:hover {
  background-color: #35916e;
}
</style>