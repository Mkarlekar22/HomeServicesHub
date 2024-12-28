
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import LoginPage from '../src/Pages/Auth/LoginPage';
import ForgotPasswordPage from '../src/Pages/Auth/ForgotPasswordPage';
import HomePage from './Pages/Home/HomePage';
import RegistrationPage from './Pages/Auth/RegistrationPage';
import ServiceDetailsPage from './components/Services/ServiceDetailsPage';
import PaymentPage from './components/Payments/PaymentPage';
import { Container } from 'react-bootstrap';
import CartPage from './Pages/cart/CartPage';
import AdminDashboard from './Pages/Auth/AdminDashboard';
import { CartProvider } from './Pages/cart/CartContext';

function App() {
  return (
    <CartProvider>
    <Router>
    <Routes>
      
      <Route path="/" element={ <HomePage/>} />
      <Route path="/admin-dashboard" element={<AdminDashboard />} />
      <Route path="/cart" element={<CartPage />} />
      <Route path="/register" element={<RegistrationPage />} />
      <Route path="/services/:serviceType" element={<ServiceDetailsPage/>} />
      <Route path="/payment" element={<PaymentPage/>} />
      <Route path="/login" element={<LoginPage />} />
      <Route path="/forgot-password" element={<ForgotPasswordPage />} />
      {/* <Route path="/dashboard" element={<Dashboard />} /> */}
      {/* Add other routes as needed */}
    </Routes>
  </Router>
  </CartProvider>
  );
}

export default App;
