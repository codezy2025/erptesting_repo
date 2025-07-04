// API Request/Response Interfaces
interface LoginRequest {
  studentId: string;
  password: string;
}

interface LoginResponse {
  success: boolean;
  message?: string;
  token?: string;
  student?: StudentData;
}

interface StudentData {
  id: string;
  name: string;
  email: string;
  department?: string;
  year?: number;
}

// Form State Interfaces
interface FormState {
  studentId: string;
  password: string;
  errors: FormErrors;
  isSubmitting: boolean;
}

interface FormErrors {
  studentId?: string;
  password?: string;
  general?: string;
}

// Component Props Interface
interface LoginFormProps {
  onLoginSuccess: (token: string, studentData: StudentData) => void;
  onForgotPassword: () => void;
}

// API Error Interface
interface ApiError {
  message: string;
  status?: number;
  timestamp?: string;
}

// Token Storage Interface
interface TokenStorage {
  setToken: (token: string) => void;
  getToken: () => string | null;
  clearToken: () => void;
}