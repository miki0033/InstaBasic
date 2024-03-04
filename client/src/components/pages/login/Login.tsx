import { Button, Input } from "@nextui-org/react";
import { Link } from "react-router-dom";

export const Login = () => {
	return (
		<div className="w-full h-full flex flex-col justify-center">
			<div className="w-8/12 mx-auto h-5/6 flex flex-col justify-around rounded-md bg-content2 border-2 border-primary-400">
				<h1 className="text-3xl ">Login</h1>

				<div className="h-2/3 flex flex-row justify-around ">
					<div className="w-6/12 h-4/5 my-auto flex flex-col justify-between ">
						<Input
							type="email"
							label="Username or E-mail"
							labelPlacement="outside"
							className="w-2/3 mx-auto p-2 pt-8"
							isClearable
							isRequired
							color="secondary"
						/>
						<Input
							type="password"
							label="Password"
							labelPlacement="outside"
							className="w-2/3 mx-auto p-2 pt-8"
							isClearable
							isRequired
							color="secondary"
						/>
						<Button className="w-1/3 mx-auto bg-secondary">Log In</Button>
					</div>
					<div className="w-4/12 h-4/5 my-auto flex flex-col justify-center">
						<img src="/logo.png" alt="Instabasic Logo" className="w-10/12 m-auto" />
					</div>
				</div>

				<div className="h-auto text-md ">
					You don't have an account?{" "}
					<Link to={"/login/register"} className="underlined text-focus">
						Register here
					</Link>
				</div>
			</div>
		</div>
	);
};
