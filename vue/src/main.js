import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';

import { library } from '@fortawesome/fontawesome-svg-core';
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
import { faCheck, faTrash } from '@fortawesome/free-solid-svg-icons';

library.add(faCheck, faTrash);

const app = createApp(App);

app
.use(router)
.use(store)
.component('font-awesome-icon', FontAwesomeIcon)
.mount('#app');