// App.js
import React from 'react';
import { Route, Routes } from 'react-router-dom';
import HeaderContainer from './components/HeaderContainer';
import BackgroundImage from './components/BackgroundImage';
import Footer from './components/Footer';
//import SliderSection from './home/SliderSection';
import LoginPage from './Member/Login';

function App() {
    return (
        <div>
            <div className="hero_area">
                <BackgroundImage />
                <HeaderContainer/>
                
            </div>
            <section class="about_section layout_padding">
                <Routes>
                    <Route path="/Login" element={<LoginPage />} />
                </Routes>
            </section>
            <Footer />
        </div>
    );
}

export default App;
