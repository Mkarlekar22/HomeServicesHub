// src/pages/Auth/ForgotPasswordPage.js


import React, { useState } from 'react';
import { Container, Form, Button, Alert } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';
import '../Auth/ForgotPassword.css'; // Import custom CSS

const ForgotPasswordPage = () => {
  const [emailId, setEmailId] = useState('');
  const [newPassword, setNewPassword] = useState('');
  const [message, setMessage] = useState('');
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  const handleForgotPassword = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError('');
    setMessage('');

    try {
      // Replace with your forgot password API endpoint
      const response = await fetch('http://localhost:8081/api/forgot-password', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ emailId, newPassword }),
      });

      const data = await response.json();

      if (response.ok) {
        setMessage('Password reset instructions sent to your email.');
        setTimeout(() => navigate('/login'), 3000); // Redirect after 3 seconds
      } else {
        setError(data.message || 'Failed to send instructions');
      }
    } catch (err) {
      setError('An error occurred');
    } finally {
      setLoading(false);
    }
  };

  return (
    <Container className="forgot-password-container">
      <h2 className="text-center mb-4">Forgot Password</h2>
      {error && <Alert variant="danger">{error}</Alert>}
      {message && <Alert variant="success">{message}</Alert>}
      <Form onSubmit={handleForgotPassword}>
        <Form.Group controlId="formEmail">
          <Form.Label>Email address</Form.Label>
          <Form.Control
            type="emailId"
            placeholder="Enter your email"
            value={emailId}
            onChange={(e) => setEmailId(e.target.value)}
            required
          />
        </Form.Group>
        <Form.Group controlId="formPassword">
          <Form.Label>New Password</Form.Label>
          <Form.Control
            type="newPassword"
            placeholder="Enter new password"
            value={newPassword}
            onChange={(e) => setNewPassword(e.target.value)}
            required
          />
        </Form.Group>

        <Button variant="primary" type="submit" disabled={loading}>
          {loading ? 'Sending...' : 'Send Instructions'}
        </Button>
      </Form>
    </Container>
  );
};

export default ForgotPasswordPage;
