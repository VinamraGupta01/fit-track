import { createContext, useContext, useState } from 'react';
import api from '../api/axios';
const AuthContext=createContext(null);
export function AuthProvider({children}){const [user,setUser]=useState(()=>JSON.parse(localStorage.getItem('fittrack_user')||'null')); async function login(email,password){const {data}=await api.post('/auth/login',{email,password}); localStorage.setItem('fittrack_token',data.data.accessToken); localStorage.setItem('fittrack_user',JSON.stringify(data.data)); setUser(data.data);} async function register(name,email,password){const {data}=await api.post('/auth/register',{name,email,password}); localStorage.setItem('fittrack_token',data.data.accessToken); localStorage.setItem('fittrack_user',JSON.stringify(data.data)); setUser(data.data);} function logout(){localStorage.clear(); setUser(null)} return <AuthContext.Provider value={{user,setUser,login,register,logout}}>{children}</AuthContext.Provider>}
export const useAuth=()=>useContext(AuthContext);
