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
      <div class="datechosser">
        <div class="dateitemchooser">
          <label for="date-input">Seleccione Fecha de Inicio::</label>
          <input type="datetime-local" id="date-input" v-model="selectedDateTimeStart" />
        </div>
        <div class="dateitemchooser">
          <label for="date-input">Seleccione Fecha de Fin:</label>
          <input type="datetime-local" id="date-input" v-model="selectedDateTimeEnd" />
        </div>
      </div>
      <button @click="addTask">Agregar</button>

      <ul>
        <li v-for="task in tasks" :key="task.id" class="task-item">
          <div class="task-info">
            <span :class="{ completed: task.completed }">{{ task.name }}</span>
            <span class="dates">Inicio: {{ this.formatDate(task.startDate) }} Fin: {{ this.formatDate(task.endDate) }}</span>
          </div>
          <div>
            <button @click="toggleTask(task)" class="icon-button">
              <font-awesome-icon :icon="['fas', 'check']" />
            </button>
            <button @click="deleteTask(task.id)" class="icon-button">
              <font-awesome-icon :icon="['fas', 'trash']" />
            </button>
          </div>
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
    formatDate(date){
      return date.slice(0, 10) + " " + date.slice(11, 16);
    },
    async fetchTasks() {
      try {
        const response = await axios.get('http://localhost:8080/task/list', {params: {userEmail: localStorage.getItem('userEmail')}}, 
          {
            headers: {'Content-Type': 'application/'}
          }
        );
        if(response.data !== null){
          this.tasks = response.data.frame;
        }
        
      } catch (error) {
        console.error('Error al obtener las tareas:', error);
      }
    },
    async addTask() {
      if (!this.newTask) return;
      if(!this.selectedDateTimeStart) return;
      if(!this.selectedDateTimeEnd) return;
      try {
        const now = new Date();
        const formattedTimestamp = now.toISOString().slice(0, 19);
        console.log(formattedTimestamp);
        const response = await axios.post('http://localhost:8080/task/addTask', { 
          userEmail: localStorage.getItem('userEmail'), 
          name: this.newTask, 
          description: this.newTask,
          createdDate: formattedTimestamp,
          startDate: this.selectedDateTimeStart.length == 16 ? this.selectedDateTimeStart + ":00" : this.selectedDateTimeStart,
          endDate: this.selectedDateTimeEnd.length == 16 ? this.selectedDateTimeEnd + ":00" : this.selectedDateTimeEnd,
          completed: false }, {
          headers: {
            'Content-Type': 'application/json'
          }

        });
        //this.tasks.push(response.data);
        console.log(response.data);
        await this.fetchTasks();
        this.newTask = '';
        this.selectedDateTimeStart = '';
        this.selectedDateTimeEnd = '';
      } catch (error) {
        console.error('Error al agregar la tarea:', error);
      }
    },
    async toggleTask(task) {
      try {
        task.completed = true;
        const response = await axios.post(`http://localhost:8080/task/updateTask`, {
          ...task
        });
        console.log(response.data);
        //task.completed = response.data.completed;
      } catch (error) {
        console.error('Error al actualizar la tarea:', error);
      }
    },
    async deleteTask(taskId) {
      try {
        await axios.post(`http://localhost:8080/task/deleteTask`, {taskId: taskId},
        {
          headers: {
              'Content-Type': 'application/json'
            }
        }
        );
        //this.tasks = this.tasks.filter(task => task.id !== taskId);
        await this.fetchTasks();
      } catch (error) {
        console.error('Error al eliminar la tarea:', error);
      }
    },
    logout() {
      localStorage.removeItem("userEmail");
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
  /*color: #35916e;*/
  color: #fff;
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

.datechosser{
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.task-item {
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.task-info{
  display: flex;
  flex-direction: column;
}
.dates {
  font-size: 0.8em; /* Adjust the font size to be smaller */
  color: #777; /* Optional: Add a lighter color to differentiate */
}
</style>