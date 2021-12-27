import React from "react";
import './Dashboard.css';
import axios from 'axios';
import { useState, useEffect } from 'react';

import {
    BarChart,
    Bar,
    XAxis,
    YAxis,
    CartesianGrid,
    Tooltip,
    Legend
} from "recharts";

// const data = [
//     {
//         client_id: "DBS001",
//         total_buy_value: 40000,
//         total_sell_value: 24000
//     },
//     {
//         client_id: "DBS002",
//         total_buy_value: 20000,
//         total_sell_value: 25000
//     },
//     {
//         client_id: "DBS003",
//         total_buy_value: 30000,
//         total_sell_value: 20000
//     },
//     {
//         client_id: "DBS004",
//         total_buy_value: 50000,
//         total_sell_value: 24000
//     }

// ];

export default function Dashboard() {

    const [clientData, setClientData] = useState([]);
    const [clientLoad, setClientLoad] = useState(false);

    const [custodianData, setCustodianData] = useState([]);
    const [custodianLoad, setCustodianLoad] = useState(false);

    useEffect(() => {
        axios.get("/dashboard/client")
            .then(res => {
                setClientData(res.data);
                console.log(res.data)
                setClientLoad(true);
            });

        axios.get("/dashboard/custodian")
            .then(res => {
                setCustodianData(res.data);
                console.log(res.data)
                setCustodianLoad(true);
            });

    }, []);

    return (
        <>{clientLoad && custodianLoad &&
            <>
                <div className="left-chart">
                    <h3 className="heading">Client wise Total Buy and Sell Value</h3>
                    <BarChart
                        width={500}
                        height={300}
                        data={clientData}
                        margin={{
                            top: 5,
                            right: 30,
                            left: 20,
                            bottom: 5
                        }}
                    >
                        <CartesianGrid strokeDasharray="3 3" />
                        <XAxis dataKey="clientId" />
                        <YAxis />
                        <Tooltip />
                        <Legend />
                        <Bar dataKey="totalBuyValue" fill="#8884d8" />
                        <Bar dataKey="totalSellValue" fill="#82ca9d" />
                    </BarChart>
                </div>
                <div className="right-chart">
                    <h3 className="heading">Custodian wise Total Buy and Sell Value</h3>
                    <BarChart
                        width={500}
                        height={300}
                        data={custodianData}
                        margin={{
                            top: 5,
                            right: 30,
                            left: 20,
                            bottom: 5
                        }}
                    >
                        <CartesianGrid strokeDasharray="3 3" />
                        <XAxis dataKey="custodianId" />
                        <YAxis />
                        <Tooltip />
                        <Legend />
                        <Bar dataKey="totalBuyValue" fill="#8884d8" />
                        <Bar dataKey="totalSellValue" fill="#82ca9d" />
                    </BarChart>
                </div>
            </>
        }
        </>
    );
}
