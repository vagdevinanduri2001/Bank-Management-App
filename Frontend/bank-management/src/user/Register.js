import { IconButton, InputAdornment, TextField } from '@mui/material'
import React, { useState } from 'react';
import AccountCircleIcon from '@mui/icons-material/AccountCircle';

import Visibility from '@mui/icons-material/Visibility';
import VisibilityOff from '@mui/icons-material/VisibilityOff';

export default function Register() {

    const [showPassword, setShowPassword] = React.useState(false);

    const handlePasswordVisibility = () => {
        setShowPassword(!showPassword);
    };

    const [user, setUser] = useState({
        userName: "",
        password: ""
    });

    const onInputChange = (e) => {
        setUser({ ...user, [e.target.name]: e.target.value })
    }


    return (
        <div className='container'>
            <div className='row'>
                <div className='col-md-6 offset-md-3 border rounded p-4 mt-2 shadow'>
                    <h2 className='text-center m-4'>Register User</h2>


                    <form>
                        <br /><br />


                        <TextField type='text' id='userName' name='userName'
                            onChange={(e) => onInputChange(e)}
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
                            onChange={(e) => onInputChange(e)}
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

                        <button type='submit'>Login</button>

                    </form>




                </div>
            </div>

        </div>
    )
}

