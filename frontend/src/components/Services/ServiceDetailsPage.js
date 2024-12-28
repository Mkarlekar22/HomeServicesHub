import React, { useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { Container, Button, Card, Row, Col } from 'react-bootstrap';
import './ServiceDetailsPage.css'; // Ensure to include this for styling

const packages = {
  PLUMBING: [
    { id: 1, name: 'Basic Plumbing', price: 100, image: '/images/plumbing.jpeg' },
    { id: 2, name: 'Standard Plumbing', price: 200, image: '/images/plumbing.jpeg' },
    { id: 3, name: 'Premium Plumbing', price: 300, image: '/images/plumbing.jpeg' },
  ],
  // Add other categories and packages as needed
};

const ServiceDetailsPage = () => {
  const { serviceType } = useParams();
  const [cart, setCart] = useState([]);
  const navigate = useNavigate();

  const handleAddToCart = (pkg) => {
    setCart([...cart, pkg]);
  };

  const handleCheckout = () => {
    navigate('/checkout');
  };

  const servicePackages = packages[serviceType] || [];

  return (
    <Container>
      <h2>{serviceType} Packages</h2>
      <Row>
        {servicePackages.map((pkg) => (
          <Col key={pkg.id} md={4} className="mb-4">
            <Card>
              <Card.Img variant="top" src={pkg.image} className="service-image" alt={pkg.name} />
              <Card.Body>
                <Card.Title>{pkg.name}</Card.Title>
                <Card.Text>Price: ${pkg.price}</Card.Text>
                <Button onClick={() => handleAddToCart(pkg)}>Add to Cart</Button>
              </Card.Body>
            </Card>
          </Col>
        ))}
      </Row>
      <Button onClick={handleCheckout} variant="primary" className="mt-3">Checkout</Button>
    </Container>
  );
};

export default ServiceDetailsPage;
