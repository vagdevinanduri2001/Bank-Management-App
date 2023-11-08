import { Button, IconButton, InputAdornment, TextField } from '@mui/material'
import React, { useState } from 'react';
import AccountCircleIcon from '@mui/icons-material/AccountCircle';

import Visibility from '@mui/icons-material/Visibility';
import VisibilityOff from '@mui/icons-material/VisibilityOff';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

export default function Login() {
    const [showPassword, setShowPassword] = React.useState(false);

    let navigate = useNavigate();

    const handlePasswordVisibility = () => {
        setShowPassword(!showPassword);
    };

    const [user, setUser] = useState({
        userName: "",
        password: ""
    });
    const { userName, password } = user

    const [data, setdata] = useState({});
    const onSubmit = (e) => {
        e.preventDefault();
        axios.post("http://localhost:9090/customer/login", user)
            // .then((response) => response.json())
            .then(response => {

                // setdata(response);
                console.log("response in", response);
                // console.log("data in", data);
                localStorage.setItem("token", JSON.stringify(response.data.token));
                localStorage.setItem("loggedInUser", JSON.stringify(response.data.customer));
                console.log("loggedInUser: ", localStorage.getItem("token"))
            })
            .catch(error => console.log(error));
        console.log("data", data);
        navigate("/");
    }

    const onInputChange = (e) => {
        setUser({ ...user, [e.target.name]: e.target.value });
    };


    return (
        <div className='container'>
            <div className='row'>
                <div className='col-md-6 offset-md-3 border rounded p-4 mt-2 shadow'>
                    <h2 className='text-center m-4'>Login Here!</h2><br />

                    <form onSubmit={(e) => onSubmit(e)}>
                        <TextField type='text' id='userName' name='userName'
                            value={userName}
                            onChange={onInputChange}
                            InputProps={{
                                endAdornment: (
                                    <InputAdornment position="end">
                                        <IconButton>
                                            <AccountCircleIcon />
                                        </IconButton>
                                    </InputAdornment>
                                ),
                            }}
                            label='userName' required /><br /><br />

                        <TextField
                            type={showPassword ? 'text' : 'password'}
                            onChange={onInputChange}
                            value={password}
                            id='password'
                            name='password'
                            label="Password"
                            required
                            InputProps={{
                                endAdornment: (
                                    <InputAdornment position="end">
                                        <IconButton onClick={handlePasswordVisibility}>
                                            {showPassword ? <Visibility /> : <VisibilityOff />}
                                        </IconButton>
                                    </InputAdornment>
                                ),
                            }}
                        />
                        <br /><br />

                        <Button type='submit' variant='contained'>Login</Button>

                    </form>

                </div>


            </div>
        </div>


    )
}
