import axios from 'axios';
import { useState,useEffect } from 'react';
import Button from 'react-bootstrap/Button';
import Slideshow from '../slideshow/Slideshow';
import {useNavigate} from 'react-router-dom';
import img1 from '../images/img1.jpg';
import './Home.css';
import React from 'react';

function Home() {
    const [ items, setItems] = useState([]);
    const[load,setLoad] = useState(false);
    const [top,setTop] = useState(-125);

    useEffect(()=>{
        axios.get("/instrument")
        .then(res=>{
            setItems(res.data);
            console.log(res.data);
            setLoad(true);
            setTop(top-310)
        });
    },[]);

    const navigate = useNavigate();
    const redirect = ()=>{
        navigate("/orders");
    }

    return (
        <div className="container">
            
            <h1>Buy the Fear - Sell the Greed</h1>

            <Button className="bt" active variant="success" onClick={redirect}><b>Place Your Order</b></Button>
            <br/>
            {load &&
            <>
                <h2>Available Stocks</h2>
                <Slideshow items={items}/>
            </>
            }
            <img src={img1} style={{marginTop:`${top}px`}} />
        </div>
    );
}

export default Home;