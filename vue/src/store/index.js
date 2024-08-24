import { createStore } from 'vuex';

const store = createStore({
  state() {
    return {
      isAuthenticated: localStorage.getItem("userEmail") !== undefined  && localStorage.getItem("userEmail") !== null ? true : false
    };
  },
  mutations: {
    authenticate(state) {
      state.isAuthenticated = true;
    },
    logout(state) {
      state.isAuthenticated = false;
    }
  },
  actions: {
    login({ commit }) {
      // Simulación de autenticación
      commit('authenticate');
    },
    logout({ commit }) {
      commit('logout');
    }
  }
});

export default store;
