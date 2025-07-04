import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios, { AxiosError, AxiosResponse } from 'axios';
import { Box, Button, TextField, Typography, Link, CircularProgress, Alert } from '@mui/material';
import { styled } from '@mui/system';

// Interface for login form data
interface LoginFormData {
  studentId: string;
  password: string;
}

// Interface for API response
interface ApiResponse {
  token: string;
  message?: string;
}

// Interface for API error response
interface ApiError {
  message: string;
  errors?: Record<string, string[]>;
}

const StyledForm = styled('form')({
  display: 'flex',
  flexDirection: 'column',
  gap: '1rem',
  maxWidth: '400px',
  margin: '0 auto',
  padding: '2rem',
  borderRadius: '8px',
  boxShadow: '0 2px 10px rgba(0,0,0,0.1)',
});

const StudentLoginPortalPage: React.FC = () => {
  const navigate = useNavigate();
  const [formData, setFormData] = useState<LoginFormData>({
    studentId: '',
    password: '',
  });
  const [errors, setErrors] = useState<Partial<LoginFormData>>({});
  const [isLoading, setIsLoading] = useState(false);
  const [apiError, setApiError] = useState<string | null>(null);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormData(prev => ({
      ...prev,
      [name]: value,
    }));
    // Clear error when user starts typing
    if (errors[name as keyof LoginFormData]) {
      setErrors(prev => ({
        ...prev,
        [name]: undefined,
      }));
    }
  };

  const validateForm = (): boolean => {
    const newErrors: Partial<LoginFormData> = {};
    
    if (!formData.studentId.trim()) {
      newErrors.studentId = 'Student ID is required';
    }
    
    if (!formData.password) {
      newErrors.password = 'Password is required';
    } else if (formData.password.length < 6) {
      newErrors.password = 'Password must be at least 6 characters';
    }
    
    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setApiError(null);
    
    if (!validateForm()) return;
    
    setIsLoading(true);
    
    try {
      const response: AxiosResponse<ApiResponse> = await axios.post('/api/login', formData);
      
      // Store JWT token securely (httpOnly cookies would be better in production)
      localStorage.setItem('jwtToken', response.data.token);
      
      // Redirect to dashboard on success
      navigate('/dashboard');
    } catch (error) {
      const axiosError = error as AxiosError<ApiError>;
      if (axiosError.response) {
        if (axiosError.response.status === 401) {
          setApiError('Invalid credentials. Please try again.');
        } else {
          setApiError(axiosError.response.data.message || 'Login failed. Please try again.');
        }
      } else {
        setApiError('Network error. Please check your connection.');
      }
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <Box sx={{ 
      display: 'flex', 
      flexDirection: 'column', 
      minHeight: '100vh', 
      justifyContent: 'center',
      padding: '1rem',
    }}>
      <Typography variant="h4" component="h1" align="center" gutterBottom>
        Student Login Portal
      </Typography>
      
      <StyledForm onSubmit={handleSubmit}>
        {apiError && (
          <Alert severity="error" sx={{ mb: 2 }}>
            {apiError}
          </Alert>
        )}
        
        <TextField
          label="Student ID"
          name="studentId"
          value={formData.studentId}
          onChange={handleChange}
          error={!!errors.studentId}
          helperText={errors.studentId}
          fullWidth
          margin="normal"
        />
        
        <TextField
          label="Password"
          name="password"
          type="password"
          value={formData.password}
          onChange={handleChange}
          error={!!errors.password}
          helperText={errors.password}
          fullWidth
          margin="normal"
        />
        
        <Box sx={{ display: 'flex', justifyContent: 'flex-end' }}>
          <Link href="/forgot-password" variant="body2">
            Forgot password?
          </Link>
        </Box>
        
        <Button
          type="submit"
          variant="contained"
          color="primary"
          disabled={isLoading}
          fullWidth
          sx={{ mt: 2 }}
        >
          {isLoading ? (
            <CircularProgress size={24} color="inherit" />
          ) : (
            'Login'
          )}
        </Button>
      </StyledForm>
    </Box>
  );
};

export default StudentLoginPortalPage;