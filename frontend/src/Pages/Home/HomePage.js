import React, { useState, useRef, useContext } from 'react';
import axios from 'axios';
import { Modal, Button, Container, Navbar, Nav, Card, Row, Col, Toast } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';
import ServiceCard from '../Home/ServiceCard';
import '../Home/HomePage.css';
import { FaFacebook, FaTwitter, FaInstagram, FaShoppingCart } from 'react-icons/fa';
import { CartContext } from '../cart/CartContext';

const HomePage = () => {
    const navigate = useNavigate();
    const [showModal, setShowModal] = useState(false);
   
    const [servicesList, setServicesList] = useState([]);
    const [selectedServiceType, setSelectedServiceType] = useState('');
    const [showSuccessToast, setShowSuccessToast] = useState(false);
    const [showDuplicateToast, setShowDuplicateToast] = useState(false);

    const { cart, setCart, totalAmount, setTotalAmount } = useContext(CartContext);

    
    const scrollContainerRef = useRef(null);

    const handleLoginClick = () => {
        navigate('/login'); // Navigate to the login page
    };

    

    const handleRegisterClick = () => {
        navigate('/register');
    };

    const handleCartClick = () => navigate('/cart');

    const scrollLeft = () => {
        scrollContainerRef.current.scrollBy({ left: -200, behavior: 'smooth' });
    };

    const scrollRight = () => {
        scrollContainerRef.current.scrollBy({ left: 200, behavior: 'smooth' });
    };

    const services = [
        {
            image: '/images/plumbing.jpeg',
            title: 'Plumbing Services',
            description: 'Expert plumbing services for residential and commercial needs.',
            serviceType: 'PLUMBING_SERVICE'
        },
        {
            image: '/images/elctrical.jpg',
            title: 'Electrical Services',
            description: 'Professional electrical repairs and installations.',
            serviceType: 'ELECTRICAL_SERVICE'
        },
        {
            image: '/images/cleaning.jpeg',
            title: 'Cleaning Services',
            description: 'Comprehensive cleaning services for homes and offices.',
            serviceType: 'CLEANING'
        },
        {
            image: '/images/painting.jpeg',
            title: 'Painting Services',
            description: 'High-quality painting services for interiors and exteriors.',
            serviceType: 'PAINTING_SERVICE'
        },
        {
            image: '/images/ac.jpeg',
            title: 'HVAC Services',
            description: 'Heating, ventilation, and air conditioning services.',
            serviceType: 'AC_REPAIRE_SERVICE'
        },
    ];

    const handleServiceClick = (serviceType) => {
        setSelectedServiceType(serviceType);
        fetchServicesByType(serviceType);
        setShowModal(true);
    };

    const fetchServicesByType = async (serviceType) => {
        try {
            const response = await axios.get(`http://localhost:8081/api/services/services/${serviceType}`);
            setServicesList(response.data);
        } catch (error) {
            console.error("Error fetching services by type:", error);
        }
    };

    const handleAddToCart = (service) => {
        const serviceAlreadyInCart = cart.some(cartItem => cartItem.id === service.id);

        if (serviceAlreadyInCart) {
            setShowDuplicateToast(true);
        } else {
            setCart([...cart, service]);
            setTotalAmount(totalAmount + service.price);
            setShowSuccessToast(true);
        }
    };

    const handleClose = () => setShowModal(false);

    return (
        <>
            <Navbar bg="dark" variant="dark" expand="lg">
                <Container>
                    <Navbar.Brand href="#home">HomeService Hub</Navbar.Brand>
                    <Navbar.Toggle aria-controls="basic-navbar-nav" />
                    <Navbar.Collapse id="basic-navbar-nav">
                        <Nav className="mr-auto">
                            <Nav.Link href="#features">Features</Nav.Link>
                            <Nav.Link href="#pricing">Pricing</Nav.Link>
                            <Nav.Link href="#contact">Contact</Nav.Link>
                        </Nav>
                        <Button variant="outline-light" onClick={handleLoginClick}>Login</Button>
                        <Button variant="secondary" onClick={handleRegisterClick} className="ms-2">Register</Button>
                        <FaShoppingCart size={24} className="ms-3 text-light" onClick={handleCartClick} style={{ cursor: 'pointer' }} />
                    </Navbar.Collapse>
                </Container>
            </Navbar>

            <header className="hero-section text-center text-white bg-primary py-5">
                <Container>
                    <h1 className="display-4">Welcome to HomeService Hub</h1>
                    <p className="lead">Your one-stop solution for all home services.</p>
                    <Button variant="light" size="lg">Get Started</Button>
                </Container>
            </header>

            <Container className="mt-5">
                <button className="scroll-arrow left" onClick={scrollLeft}>&lt;</button>
                <div className="horizontal-scroll-container" ref={scrollContainerRef}>
                    {services.map((service, index) => (
                        <div key={index} className="service-card-col" onClick={() => handleServiceClick(service.serviceType)}>
                            <ServiceCard
                                image={service.image}
                                title={service.title}
                                description={service.description}
                            />
                        </div>
                    ))}
                </div>
                <button className="scroll-arrow right" onClick={scrollRight}>&gt;</button>
            </Container>

            <section className="py-5">
    <Container>
        <Row>
            <Col md={4}>
                <Card className="custom-card" style={{ backgroundColor: '#f9f5ff' }}> {/* Light purple */}
                    <Card.Body>
                        <Card.Title>On time Services</Card.Title>
                        <Card.Text>
                            "Experience the difference with our app, where punctuality meets excellence. With a commitment to delivering on-time service every time, we ensure that your needs are met promptly and professionally. Say goodbye to delays and hello to reliability, because your time matters to us."
                        </Card.Text>
                    </Card.Body>
                </Card>
            </Col>
            <Col md={4}>
                <Card className="custom-card" style={{ backgroundColor: '#f5fff7' }}> {/* Light green */}
                    <Card.Body>
                        <Card.Title>Trusted by Millions</Card.Title>
                        <Card.Text>
                            "Trusted by millions, our app delivers seamless and reliable services that you can count on. Join a global community that values quality and convenience, with every booking backed by our commitment to excellence. Your satisfaction is our priority, and with a million smiles and counting, we're just getting started."
                        </Card.Text>
                    </Card.Body>
                </Card>
            </Col>
            <Col md={4}>
                <Card className="custom-card" style={{ backgroundColor: '#fff7f0' }}> {/* Light orange */}
                    <Card.Body>
                        <Card.Title>Genuine services</Card.Title>
                        <Card.Text>
                            "Choose authenticity with our app, where genuine services are our promise to you. We connect you with trusted professionals who deliver quality you can rely on, every time. Experience the peace of mind that comes with knowing you're getting the real deal—because integrity is at the heart of what we do."
                        </Card.Text>
                    </Card.Body>
                </Card>
            </Col>
        </Row>
    </Container>
</section>

            {/* About Us Section */}
            <section className="about-us-section py-5">
                <Container>
                    <Row className="align-items-center">
                        <Col md={6}>
                            <img src="\images\kl.jpg" alt="About Us" className="img-fluid rounded" />
                        </Col>
                        <Col md={6}>
                            <h2>About Us</h2>
                            <p>HomeService Hub is dedicated to providing top-notch home services that meet your needs. From plumbing and electrical work to cleaning and HVAC services, our experienced professionals are here to help.</p>
                            <p>Our mission is to deliver reliable and high-quality services that you can trust. We prioritize customer satisfaction and strive to exceed your expectations with every service.</p>
                        </Col>
                    </Row>
                </Container>
            </section>

            <footer className="bg-dark text-white py-3 text-center m-0"> {/* Remove top margin */}
    <Container>
        <Row>
            <Col md={6}>
                <p>&copy; 2024 HomeService Hub. All rights reserved.</p>
            </Col>
            <Col md={6}>
                <div className="social-icons">
                    <a href="https://www.facebook.com" target="_blank" rel="noopener noreferrer" className="me-3 text-white">
                        <FaFacebook size={30} />
                    </a>
                    <a href="https://www.twitter.com" target="_blank" rel="noopener noreferrer" className="me-3 text-white">
                        <FaTwitter size={30} />
                    </a>
                    <a href="https://www.instagram.com" target="_blank" rel="noopener noreferrer" className="me-3 text-white">
                        <FaInstagram size={30} />
                    </a>
                </div>
            </Col>
        </Row>
    </Container>
</footer>


            <Modal show={showModal} onHide={handleClose} size="lg" centered>
                <Modal.Header closeButton>
                    <Modal.Title>Available Services</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Container>
                        <Row>
                            {servicesList.map((service, index) => (
                                <Col md={4} key={index} className="mb-3">
                                    <Card>
                                        <Card.Body>
                                            <Card.Title>{service.serviceName}</Card.Title>
                                            <Card.Text>{service.description}</Card.Text>
                                            <Card.Text>Price: ${service.price}</Card.Text>
                                            <Button className="service-button" onClick={() => handleAddToCart(service)}>Add to Cart</Button>
                                        </Card.Body>
                                    </Card>
                                </Col>
                            ))}
                        </Row>
                        <Button variant="primary" onClick={handleCartClick}>View Cart</Button>
                    </Container>
                </Modal.Body>
            </Modal>

          
        </>
    );
};

export default HomePage;
