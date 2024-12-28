import React, { useContext, useState } from 'react';
import { Container, Row, Col, Button, Card, Modal, Form, Toast } from 'react-bootstrap';
import { CartContext } from '../cart/CartContext';
import { useNavigate } from 'react-router-dom';
import './CartPage.css';


const CartPage = () => {
    const { cart, totalAmount, setCart, setTotalAmount } = useContext(CartContext);
    const navigate = useNavigate();
    const [showModal, setShowModal] = useState(false);
    const [showSuccessToast, setShowSuccessToast] = useState(false);

    const handleExploreServices = () => {
        navigate('/'); // Navigate back to the home page
    };

    const handleCheckout = () => {
        setShowModal(true);
    };

    const handlePayment = (paymentMethod) => {
        // Implement payment logic based on the selected payment method
        console.log(`Payment method selected: ${paymentMethod}`);
        setShowModal(false);
        setShowSuccessToast(true);
        // Clear the cart after payment is successful
        setCart([]);
        setTotalAmount(0);
    };

    return (
        <Container className="py-5">
            <h2>Your Cart</h2>
            {cart.length === 0 ? (
                <div className="text-center my-5">
                    <img src="/images/cart.png" alt="Empty Cart" className="img-fluid mb-3" />
                    <p>Your cart is empty</p>
                    <Button className="service-button" onClick={handleExploreServices}>Explore Services</Button>
                </div>
            ) : (
                <div>
                    <Row>
                        {cart.map((service, index) => (
                            <Col md={4} key={index} className="mb-3">
                                <Card>
                                    <Card.Body>
                                        <Card.Title>{service.serviceName}</Card.Title>
                                        <Card.Text>{service.description}</Card.Text>
                                        <Card.Text>Price: ${service.price}</Card.Text>
                                    </Card.Body>
                                </Card>
                            </Col>
                        ))}
                    </Row>
                    <div className="text-center mt-4">
                        <h3>Total Amount: ${totalAmount}</h3>
                        <Button className="checkout-button" onClick={handleCheckout}>Checkout</Button>
                    </div>
                </div>
            )}

            <Modal show={showModal} onHide={() => setShowModal(false)}>
                <Modal.Header closeButton>
                    <Modal.Title>Select Payment Method</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form>
                        <Form.Group>
                            <Button className="mb-3 w-100" onClick={() => handlePayment('UPI')}>Pay with UPI</Button>
                        </Form.Group>
                        <Form.Group>
                            <Button className="mb-3 w-100" onClick={() => handlePayment('Card')}>Pay with Card</Button>
                        </Form.Group>
                        <Form.Group>
                            <Button className="w-100" onClick={() => handlePayment('Cash on Delivery')}>Cash on Delivery</Button>
                        </Form.Group>
                    </Form>
                </Modal.Body>
            </Modal>

            <Toast onClose={() => setShowSuccessToast(false)} show={showSuccessToast} delay={3000} autohide>
                <Toast.Body>Payment successful! Thank you for your purchase.</Toast.Body>
            </Toast>
        </Container>
    );
};

export default CartPage;
