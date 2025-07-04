import React, { useState } from 'react';
import Login from './Login';
import AdminDashboard from './AdminDashboard';

function App() {
  const [loggedIn, setLoggedIn] = useState(false);
  return loggedIn ? <AdminDashboard /> : <Login onLogin={() => setLoggedIn(true)} />;
}

export default App;
