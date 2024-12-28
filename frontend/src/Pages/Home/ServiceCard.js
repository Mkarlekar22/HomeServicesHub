import React from 'react';
import { Card } from 'react-bootstrap';

import '../Home/HomePage.css'

const ServiceCard = ({ image, title, description }) => {
    return (
        <Card style={{ width: '18rem' }} className="m-3">
            <Card.Img variant="top" src={image} />
            <Card.Body>
                <Card.Title>{title}</Card.Title>
                <Card.Text>{description}</Card.Text>
            </Card.Body>
        </Card>
    );
};

export default ServiceCard;
