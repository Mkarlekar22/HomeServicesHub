// src/pages/Auth/LoginPage.js

import React, { useState } from 'react';
import axios from 'axios';
import { Container, Form, Button, Alert } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';
import '../Auth/LoginPage.css'; // Import custom CSS

const LoginPage = () => {
  const [emailId, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError('');

    try {
      // Replace with your login API endpoint
      const response = await fetch('http://localhost:8081/users/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ emailId, password }),
      });

      const data = await response.json();

      if (response.ok) {
        // Store token or session information
        localStorage.setItem('token', data.token);
        navigate('/'); // Redirect to the dashboard or home page
      } else {
        setError(data.message || 'Login failed');
      }
    } catch (err) {
      setError('An error occurred');
    } finally {
      setLoading(false);
    }


    
  };

  const handleForgotPassword = () => {
    navigate('/forgot-password'); // Redirect to the forgot password page
  };

  const handleRegister = () => {
    navigate('/register');
  };

  return (
    <Container className="login-container">
      <h2 className="text-center mb-4">Login</h2>
      {error && <Alert variant="danger">{error}</Alert>}
      <Form onSubmit={handleLogin}>
        <Form.Group controlId="formEmail">
          <Form.Label>Email address</Form.Label>
          <Form.Control
            type="email"
            placeholder="Enter your email"
            value={emailId}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
        </Form.Group>
        <Form.Group controlId="formPassword">
          <Form.Label>Password</Form.Label>
          <Form.Control
            type="password"
            placeholder="Enter your password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </Form.Group>
        <Button variant="primary" type="submit" disabled={loading}>
          {loading ? 'Logging in...' : 'Login'}
        </Button>
        <Button
          variant="link"
          onClick={handleForgotPassword}
          className="mt-2"
        >
          Forgot Password?
        </Button>
        <Button
          variant="link"
          onClick={handleRegister}
          className="mt-2"
        >
          Register
        </Button>
      </Form>
    </Container>
  );
};

export default LoginPage;
