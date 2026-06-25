import axios from 'axios';
const api = axios.create({ baseURL: import.meta.env.VITE_API_BASE_URL || 'http://localhost:8080/api' });
api.interceptors.request.use((config)=>{ const token=localStorage.getItem('fittrack_token'); if(token) config.headers.Authorization=`Bearer ${token}`; return config; });
api.interceptors.response.use(r=>r, err=>{ if(err?.response?.status===401){ localStorage.removeItem('fittrack_token'); localStorage.removeItem('fittrack_user'); } return Promise.reject(err); });
export default api;
