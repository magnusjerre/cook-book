import React from 'react';
import { useAuth0 } from '@auth0/auth0-react';

const LoginButton = () => {
    const { loginWithRedirect } = useAuth0();

    return <button className="large-button" onClick={() => loginWithRedirect({
        audience: "https://baje-cook-book.herokuap.com",
        scope: "read:yolosecret",
    })}>Log inn</button>;
};

export default LoginButton;
