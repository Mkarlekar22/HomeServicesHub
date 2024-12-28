import React, { createContext, useState } from 'react';

export const CartContext = createContext();

export const CartProvider = ({ children }) => {
    const [cart, setCart] = useState([]);
    const [totalAmount, setTotalAmount] = useState(0);

    return (
        <CartContext.Provider value={{ cart, setCart, totalAmount, setTotalAmount }}>
            {children}
        </CartContext.Provider>
    );
};
