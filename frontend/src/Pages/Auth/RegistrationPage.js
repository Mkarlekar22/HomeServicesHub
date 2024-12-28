// src/pages/Auth/RegistrationPage.js

import React, { useState } from 'react';
import { Container, Form, Button, Alert } from 'react-bootstrap';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

import '../Auth/RegistrationPage.css'; 
const RegistrationPage = () => {
  const [formData, setFormData] = useState({
    fname: '',
    lname: '',
    emailId: '',
    contactNumber: '',
    password: '',
    gender: '',
    role: 'ROLE_CUSTOMER',
    serviceType: 'NULL',
    address: {
      houseNo: '',
      street: '',
      city: '',
      state: '',
      postalCode: '',
      country: '',
    },
  });

  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value } = e.target;
    if (name.includes('.')) {
      const [parent, child] = name.split('.');
      setFormData({
        ...formData,
        [parent]: {
          ...formData[parent],
          [child]: value,
        },
      });
    } else {
      setFormData({
        ...formData,
        [name]: value,
      });
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError('');

    try {
      const response = await axios.post('http://localhost:8081/users/register', formData);

      if (response.status === 200) {
        navigate('/login');
      } else {
        setError('Registration failed');
      }
    } catch (err) {
      setError('An error occurred');
    } finally {
      setLoading(false);
    }
  };

  return (
    <Container className="registration-container">
      <h2 className="text-center mb-4">Register</h2>
      {error && <Alert variant="danger">{error}</Alert>}
      <Form onSubmit={handleSubmit}>
        <Form.Group controlId="formFirstName">
          <Form.Label>First Name</Form.Label>
          <Form.Control
            type="text"
            placeholder="Enter your first name"
            name="fname"
            value={formData.fname}
            onChange={handleChange}
            required
          />
        </Form.Group>
        <Form.Group controlId="formLastName">
          <Form.Label>Last Name</Form.Label>
          <Form.Control
            type="text"
            placeholder="Enter your last name"
            name="lname"
            value={formData.lname}
            onChange={handleChange}
            required
          />
        </Form.Group>
        <Form.Group controlId="formEmail">
          <Form.Label>Email</Form.Label>
          <Form.Control
            type="email"
            placeholder="Enter your email"
            name="emailId"
            value={formData.emailId}
            onChange={handleChange}
            required
          />
        </Form.Group>
        <Form.Group controlId="formContactNumber">
          <Form.Label>Contact Number</Form.Label>
          <Form.Control
            type="text"
            placeholder="Enter your contact number"
            name="contactNumber"
            value={formData.contactNumber}
            onChange={handleChange}
            required
          />
        </Form.Group>
        <Form.Group controlId="formPassword">
          <Form.Label>Password</Form.Label>
          <Form.Control
            type="password"
            placeholder="Enter your password"
            name="password"
            value={formData.password}
            onChange={handleChange}
            required
          />
        </Form.Group>
        <Form.Group controlId="formGender">
          <Form.Label>Gender</Form.Label>
          <Form.Control
            as="select"
            name="gender"
            value={formData.gender}
            onChange={handleChange}
            required
          >
            <option value="">Select Gender</option>
            <option value="MALE">Male</option>
            <option value="FEMALE">Female</option>
            <option value="OTHER">Other</option>
          </Form.Control>
        </Form.Group>
        <Form.Group controlId="formUserRole">
          <Form.Label>User Role</Form.Label>
          <Form.Control
            as="select"
            name="role"
            value={formData.role}
            onChange={handleChange}
            required
          >
            <option value="ROLE_CUSTOMER">Customer</option>
            <option value="ROLE_SERVICE_PROVIDER">Service Provider</option>
            <option value="ROLE_ADMIN">Admin</option>
          </Form.Control>
        </Form.Group>
        {formData.role === 'ROLE_SERVICE_PROVIDER' && (
          <Form.Group controlId="formServiceType">
            <Form.Label>Service Type</Form.Label>
            <Form.Control
              type="text"
              placeholder="Enter your service type"
              name="serviceType"
              value={formData.serviceType}
              onChange={handleChange}
              required
            />
          </Form.Group>
        )}
        <Form.Group controlId="formHouseNo">
          <Form.Label>House No</Form.Label>
          <Form.Control
            type="text"
            placeholder="Enter your house number"
            name="address.houseNo"
            value={formData.address.houseNo}
            onChange={handleChange}
            required
          />
        </Form.Group>
        <Form.Group controlId="formStreet">
          <Form.Label>Street</Form.Label>
          <Form.Control
            type="text"
            placeholder="Enter your street"
            name="address.street"
            value={formData.address.street}
            onChange={handleChange}
            required
          />
        </Form.Group>
        <Form.Group controlId="formCity">
          <Form.Label>City</Form.Label>
          <Form.Control
            type="text"
            placeholder="Enter your city"
            name="address.city"
            value={formData.address.city}
            onChange={handleChange}
            required
          />
        </Form.Group>
        <Form.Group controlId="formState">
          <Form.Label>State</Form.Label>
          <Form.Control
            type="text"
            placeholder="Enter your state"
            name="address.state"
            value={formData.address.state}
            onChange={handleChange}
            required
          />
        </Form.Group>
        <Form.Group controlId="formPostalCode">
          <Form.Label>Postal Code</Form.Label>
          <Form.Control
            type="text"
            placeholder="Enter your postal code"
            name="address.postalCode"
            value={formData.address.postalCode}
            onChange={handleChange}
            required
          />
        </Form.Group>
        <Form.Group controlId="formCountry">
          <Form.Label>Country</Form.Label>
          <Form.Control
            type="text"
            placeholder="Enter your country"
            name="address.country"
            value={formData.address.country}
            onChange={handleChange}
            required
          />
        </Form.Group>
        <Button variant="primary" type="submit" disabled={loading}>
          {loading ? 'Registering...' : 'Register'}
        </Button>
      </Form>
    </Container>
  );
};

export default RegistrationPage;
