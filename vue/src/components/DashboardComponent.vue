<template>
  <div class="dashboard-container">
    <header class="dashboard-header">
      <h1>Lista de Tareas</h1>
      <button @click="logout" class="logout-button">
        <font-awesome-icon :icon="['fas', 'sign-out-alt']" /> Logout
      </button>
    </header>
    <div class="todo-list">
      <input v-model="newTask" placeholder="Agregar una nueva tarea" maxlength="50" />
      <button @click="addTask">Agregar</button>

      <ul>
        <li v-for="task in tasks" :key="task.id">
          <span :class="{ completed: task.completed }">{{ task.name }}</span>
          <button @click="toggleTask(task)" class="icon-button">
            <font-awesome-icon :icon="['fas', 'check']" />
          </button>
          <button @click="deleteTask(task.id)" class="icon-button">
            <font-awesome-icon :icon="['fas', 'trash']" />
          </button>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
export default {
  data() {
    return {
      newTask: '',
      tasks: [],
    };
  },
  methods: {
    async fetchTasks() {
      try {
        const response = await axios.get('http://localhost:8081/tasks/all');
        this.tasks = response.data.tasks;
      } catch (error) {
        console.error('Error al obtener las tareas:', error);
      }
    },
    async addTask() {
      if (!this.newTask) return;
      try {
        const response = await axios.post('http://localhost:8081/tasks', { name: this.newTask, completed: false }, {
          headers: {
            'Content-Type': 'application/json'
          }
        });
        this.tasks.push(response.data);
        this.newTask = '';
      } catch (error) {
        console.error('Error al agregar la tarea:', error);
      }
    },
    async toggleTask(task) {
      try {
        const response = await axios.put(`http://localhost:8081/tasks/${task.id}`, {
          ...task,
          completed: !task.completed,
        });
        task.completed = response.data.completed;
      } catch (error) {
        console.error('Error al actualizar la tarea:', error);
      }
    },
    async deleteTask(taskId) {
      try {
        await axios.delete(`http://localhost:8081/tasks/${taskId}`);
        this.tasks = this.tasks.filter(task => task.id !== taskId);
      } catch (error) {
        console.error('Error al eliminar la tarea:', error);
      }
    },
    logout() {
      this.$router.push('/'); // Redirige al HomeComponent
    }
  },
  mounted() {
    this.fetchTasks();
  }
};
</script>

<style scoped>
.dashboard-container {
  background-color: #f2f8f5;
  min-height: 100vh;
  padding: 20px;
}

.dashboard-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

.logout-button {
  background-color: transparent;
  border: none;
  color: #42b983;
  cursor: pointer;
  font-size: 16px;
  display: flex;
  align-items: center;
}

.logout-button:hover {
  color: #35916e;
}

.todo-list {
  max-width: 600px;
  margin: 0 auto;
  text-align: left;
}

.completed {
  text-decoration: line-through;
}

input {
  margin-bottom: 10px;
  padding: 8px;
  width: calc(100% - 18px);
  border: 1px solid #ccc;
  border-radius: 4px;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  display: flex;
  align-items: center;
  margin-bottom: 10px;
}

li span {
  flex: 1;
}

button {
  margin-left: 5px;
  padding: 8px 12px;
  background-color: #42b983;
  color: #fff;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

button:hover {
  background-color: #35916e;
}

.icon-button {
  background-color: transparent;
  border: none;
  cursor: pointer;
  color: #42b983;
}

.icon-button:hover {
  color: #35916e;
}
</style>