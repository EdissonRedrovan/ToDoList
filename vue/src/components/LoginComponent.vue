<!-- src/components/Login.vue -->
<template>
    <div class="login-container">
      <div class="login-box">
        <img src="@/assets/ToDoList1.png" alt="Logo" class="login-logo" />
        <!-- Tab Menu -->
      <div class="tabs">
        <button :class="{ active: isLoginActive } + ' tab-button'" @click="showLogin">Login</button>
        <button :class="{ active: !isLoginActive } + ' tab-button'" @click="showSignUp">Sign Up</button>
      </div>
      <div v-if="isLoginActive">
        <h1>Login</h1>
        <form @submit.prevent="submitLogin">
          <div class="input-group">
            <input v-model="username" type="text" placeholder="Email" required />
          </div>
          <div class="input-group">
            <input v-model="password" type="password" placeholder="Password" required />
          </div>
          <button type="submit" class="login-button">Login</button>
        </form>
      </div>

      <!-- Sign Up Form -->
      <div v-else>
        <h1>Sign Up</h1>
        <form @submit.prevent="submitSignUp">
          <div class="input-group">
            <input v-model="name" type="input" placeholder="Nombre" required />
          </div>
          <div class="input-group">
            <input v-model="newEmail" type="email" placeholder="Email" required />
          </div>
          <div class="input-group">
            <input v-model="newPassword" type="password" placeholder="Password" required />
          </div>
          <div class="input-group">
            <select v-model="selectedUserType" required>
              <option disabled value="">Seleccione tipo de usuario</option>
              <option value="admin">Admin</option>
              <option value="normal">Usuario</option>
            </select>
          </div>
          <button type="submit" class="login-button">Sign Up</button>
        </form>
      </div>
      </div>
    </div>
  </template>
  
  <script>
  import { mapActions } from 'vuex';  // Importa mapActions desde vuex
  import axios from 'axios';

  export default {
    data() {
      return {
        isLoginActive: true,
        username: '',
        password: '',
        name: '',
        selectedUserType: '',
        newEmail: '',
        newPassword: ''
      };
    },
    methods: {
      ...mapActions(['login']),  // Mapea la acción login como un método
      showLogin() {
      this.isLoginActive = true;
      },
      showSignUp() {
        this.isLoginActive = false;
      },
      async submitLogin() {
        try {
          const response = await axios.post("http://localhost:8080/loginuser", {email: this.username, password: this.password}, 
            {
              headers: {
                'Content-Type': 'application/json'
                }
            }
          );
          console.log(response.data);
          if(response.data === null) {
            alert("Error al intentar realizar el login");
          }else
          if (response.data.code === '00') {
            await this.login();
            // Autenticación simulada
            console.log('redirecting to /dashboard');
            localStorage.setItem("userEmail", response.data.frame.email);
            this.$router.push({path:'/dashboard'});
          } else {
            alert(response.data.message);
          }
        } catch (error) {
          console.log("Error: " + error);
        }
        
      },
      async submitSignUp() {
        // Add logic to handle sign-up request
        try {
          const response = await axios.post("http://localhost:8080/adduser", {
            name: this.name,
            userType: this.selectedUserType,
            email: this.newEmail,
            password: this.newPassword
          }, {
            headers: {
              'Content-Type': 'application/json'
            }
          });
          if (response.data.code === '00') {
            alert('Sign up successful! Please log in.');
            this.showLogin(); // Switch back to login form
          } else {
            alert(response.data.message);
          }
        } catch (error) {
          console.log("Error: " + error);
        }
      }
    },
    mounted(){
      if(localStorage.getItem("userEmail") !== undefined && localStorage.getItem("userEmail") !== null){
        this.$router.push({path:'/dashboard'});
      }
    }
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

.tabs {
  display: flex;
  justify-content: center;
  margin-bottom: 20px;
}

.tab-button {

  width: 100%;

  padding: 10px;

  margin-right: 2px;

  background-color: gray;

  color: #fff;

  font-size: 16px;

  border: none;

  cursor: pointer;

  transition: background-color 0.3s;

}

.tab-button:hover {

  background-color: darkgray;

}

</style>