import '../Auth/Admin.css';




import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Container, Table, Button, Spinner, Alert } from 'react-bootstrap';
import { FaEdit, FaTrash } from 'react-icons/fa';

const AdminDashboard = () => {
    const [services, setServices] = useState([]);
    const [serviceProviders, setServiceProviders] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState('');

    useEffect(() => {
        const fetchData = async () => {
            try {
                const [servicesResponse, providersResponse] = await Promise.all([
                    axios.get('http://localhost:8081/api/services/services'),
                    axios.get('http://localhost:8081/api/admin/users')
                ]);
                setServices(servicesResponse.data);
                setServiceProviders(providersResponse.data);
            } catch (err) {
                setError('Failed to fetch data');
            } finally {
                setLoading(false);
            }
        };

        fetchData();
    }, []);

    return (
        <Container className="my-4">
            <h2 className="text-center mb-4">Admin Dashboard</h2>
            {loading && <Spinner animation="border" />}
            {error && <Alert variant="danger">{error}</Alert>}

            <h3>Services</h3>
            <Table striped bordered hover>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Service Name</th>
                        <th>Description</th>
                        <th>Service Type</th>
                        <th>Price</th>
                        <th>Provider</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {services.map(service => (
                        <tr key={service.id}>
                            <td>{service.id}</td>
                            <td>{service.serviceName}</td>
                            <td>{service.description}</td>
                            <td>{service.serviceType}</td>
                            <td>${service.price.toFixed(2)}</td>
                            <td>{service.user ? service.user.fname + ' ' + service.user.lname : 'N/A'}</td>
                            <td>
                                <Button variant="info" size="sm" className="me-2"><FaEdit /></Button>
                                <Button variant="danger" size="sm"><FaTrash /></Button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </Table>

            <h3 className="mt-5">Service Providers</h3>
            <Table striped bordered hover>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Email</th>
                        <th>Contact Number</th>
                        <th>Address</th>
                        <th>Service Type</th>
                    </tr>
                </thead>
                <tbody>
                    {serviceProviders.map(provider => (
                        <tr key={provider.id}>
                            <td>{provider.id}</td>
                            <td>{provider.fname}</td>
                            <td>{provider.lname}</td>
                            <td>{provider.emailId}</td>
                            <td>{provider.contactNumber}</td>
                            <td>{provider.address ? `${provider.address.street}, ${provider.address.city}, ${provider.address.state}, ${provider.address.postalCode}` : 'N/A'}</td>
                            <td>{provider.serviceType}</td>
                        </tr>
                    ))}
                </tbody>
            </Table>
        </Container>
    );
};

export default AdminDashboard;
