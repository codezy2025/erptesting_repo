const AdminDashboard = () => {
  return (
    <div style={{ padding: '2rem', backgroundColor: '#ffffff', color: '#000000', minHeight: '100vh' }}>
      <h1>Welcome, Admin</h1>
      <div style={{ display: 'grid', gridTemplateColumns: 'repeat(auto-fill, minmax(300px, 1fr))', gap: '1.5rem', marginTop: '2rem' }}>
        {/* User Management Card */}
        <div style={{ backgroundColor: '#f5005720', padding: '1.5rem', borderRadius: '8px', border: '1px solid #f5005730' }}>
          <h2>User Management</h2>
          <p>View, edit, and manage all user accounts.</p>
          <button style={{ marginTop: '1rem', padding: '0.5rem 1rem', backgroundColor: '#1976d2', color: 'white', border: 'none', borderRadius: '4px', cursor: 'pointer' }}>
            Manage Users
          </button>
        </div>

        {/* Analytics Card */}
        <div style={{ backgroundColor: '#f5005720', padding: '1.5rem', borderRadius: '8px', border: '1px solid #f5005730' }}>
          <h2>System Analytics</h2>
          <p>View usage statistics and performance metrics.</p>
          <button style={{ marginTop: '1rem', padding: '0.5rem 1rem', backgroundColor: '#1976d2', color: 'white', border: 'none', borderRadius: '4px', cursor: 'pointer' }}>
            View Analytics
          </button>
        </div>

        {/* Configuration Card */}
        <div style={{ backgroundColor: '#f5005720', padding: '1.5rem', borderRadius: '8px', border: '1px solid #f5005730' }}>
          <h2>System Configuration</h2>
          <p>Configure application settings and preferences.</p>
          <button style={{ marginTop: '1rem', padding: '0.5rem 1rem', backgroundColor: '#1976d2', color: 'white', border: 'none', borderRadius: '4px', cursor: 'pointer' }}>
            Configure System
          </button>
        </div>

        {/* Content Management Card */}
        <div style={{ backgroundColor: '#f5005720', padding: '1.5rem', borderRadius: '8px', border: '1px solid #f5005730' }}>
          <h2>Content Management</h2>
          <p>Manage website content and media uploads.</p>
          <button style={{ marginTop: '1rem', padding: '0.5rem 1rem', backgroundColor: '#1976d2', color: 'white', border: 'none', borderRadius: '4px', cursor: 'pointer' }}>
            Manage Content
          </button>
        </div>

        {/* Reports Card */}
        <div style={{ backgroundColor: '#f5005720', padding: '1.5rem', borderRadius: '8px', border: '1px solid #f5005730' }}>
          <h2>Reports</h2>
          <p>Generate and view system reports.</p>
          <button style={{ marginTop: '1rem', padding: '0.5rem 1rem', backgroundColor: '#1976d2', color: 'white', border: 'none', borderRadius: '4px', cursor: 'pointer' }}>
            View Reports
          </button>
        </div>

        {/* Notifications Card */}
        <div style={{ backgroundColor: '#f5005720', padding: '1.5rem', borderRadius: '8px', border: '1px solid #f5005730' }}>
          <h2>Notifications</h2>
          <p>Manage system notifications and alerts.</p>
          <button style={{ marginTop: '1rem', padding: '0.5rem 1rem', backgroundColor: '#1976d2', color: 'white', border: 'none', borderRadius: '4px', cursor: 'pointer' }}>
            Manage Notifications
          </button>
        </div>
      </div>
    </div>
  );
};
export default AdminDashboard;
