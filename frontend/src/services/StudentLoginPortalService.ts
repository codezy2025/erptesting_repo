// api/studentAuthService.ts
import axios, { AxiosError, AxiosResponse } from 'axios';

// TypeScript interfaces
export interface LoginRequest {
  studentId: string;
  password: string;
}

export interface LoginResponse {
  token: string;
  studentId: string;
  firstName: string;
  lastName: string;
  email: string;
  expiresIn: number;
}

export interface ErrorResponse {
  message: string;
  statusCode: number;
}

export interface ForgotPasswordRequest {
  email: string;
}

export interface ResetPasswordRequest {
  token: string;
  newPassword: string;
  confirmPassword: string;
}

const API_BASE_URL = process.env.REACT_APP_API_BASE_URL || '/api';

// Create axios instance with base config
const apiClient = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

// Add request interceptor for auth token
apiClient.interceptors.request.use((config) => {
  const token = localStorage.getItem('studentToken');
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

// Helper function to handle errors
const handleApiError = (error: AxiosError<ErrorResponse>): ErrorResponse => {
  if (error.response) {
    return {
      message: error.response.data.message || 'An error occurred',
      statusCode: error.response.status,
    };
  } else if (error.request) {
    return {
      message: 'No response received from server',
      statusCode: 0,
    };
  }
  return {
    message: error.message,
    statusCode: 0,
  };
};

// API Service functions
export const studentAuthService = {
  // Login student
  async login(credentials: LoginRequest): Promise<LoginResponse> {
    try {
      const response: AxiosResponse<LoginResponse> = await apiClient.post(
        '/login',
        credentials
      );
      // Store token securely
      localStorage.setItem('studentToken', response.data.token);
      return response.data;
    } catch (error) {
      throw handleApiError(error as AxiosError<ErrorResponse>);
    }
  },

  // Logout student
  logout(): void {
    localStorage.removeItem('studentToken');
  },

  // Check if student is authenticated
  isAuthenticated(): boolean {
    const token = localStorage.getItem('studentToken');
    return !!token; // Simplified check - in production, you'd verify token validity
  },

  // Forgot password
  async forgotPassword(email: string): Promise<void> {
    try {
      await apiClient.post('/forgot-password', { email });
    } catch (error) {
      throw handleApiError(error as AxiosError<ErrorResponse>);
    }
  },

  // Reset password
  async resetPassword(data: ResetPasswordRequest): Promise<void> {
    try {
      await apiClient.post('/reset-password', data);
    } catch (error) {
      throw handleApiError(error as AxiosError<ErrorResponse>);
    }
  },

  // Get student profile (example of protected endpoint)
  async getStudentProfile(): Promise<Omit<LoginResponse, 'token' | 'expiresIn'>> {
    try {
      const response: AxiosResponse<Omit<LoginResponse, 'token' | 'expiresIn'>> = 
        await apiClient.get('/student/profile');
      return response.data;
    } catch (error) {
      throw handleApiError(error as AxiosError<ErrorResponse>);
    }
  },

  // Update student profile (example of CRUD operation)
  async updateStudentProfile(data: {
    firstName?: string;
    lastName?: string;
    email?: string;
  }): Promise<void> {
    try {
      await apiClient.patch('/student/profile', data);
    } catch (error) {
      throw handleApiError(error as AxiosError<ErrorResponse>);
    }
  },
};

export default studentAuthService;