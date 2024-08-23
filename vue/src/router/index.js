// src/router/index.js
import { createRouter, createWebHistory } from 'vue-router';
import LoginComponent from '../components/LoginComponent.vue';
import HomeComponent from '../components/HomeComponent.vue';
import DashboardComponent from '../components/DashboardComponent.vue';
import store from '../store';

const routes = [
  { path: '/', component: HomeComponent },
  { path: '/login', component: LoginComponent },
  {
    path: '/dashboard',
    component: DashboardComponent,
    meta: { requiresAuth: true },  // Ruta protegida
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

router.beforeEach((to, from, next) => {
  const isAuthenticated = store.state.isAuthenticated;
  console.log('isAuthenticated1 => ' + isAuthenticated);
  if (to.matched.some(record => record.meta.requiresAuth) && !isAuthenticated) {
    console.log('redirecting to /login');
    next('/login'); // Redirigir al login si no está autenticado
  } else {
    console.log('redirecting to next');
    next(); // Continuar a la ruta deseada
  }
});

export default router;
