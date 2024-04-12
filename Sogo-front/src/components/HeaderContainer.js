import React from 'react';
import Header from './Header';

const HeaderContainer = ({isLogIn}) => {
    
    return (
        <Header isLogIn={isLogIn} />
    );
}

export default HeaderContainer;