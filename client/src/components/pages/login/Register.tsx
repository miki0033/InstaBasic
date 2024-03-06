import { Button, Input, ScrollShadow } from "@nextui-org/react";
import { useData } from "../../main/DataProvider";
import { ChangeEvent, useState } from "react";

const Register = () => {
	const { dispatch } = useData();
	const credentials_init = {
		firstName: "",
		lastName: "",
		userName: "",
		email: "",
		confirmEmail: "",
		password: "",
		confirmPassword: "",
		birthday: "",
	};
	const [credentials, changeCredentials] = useState(credentials_init);

	const handleInput = (e: ChangeEvent<HTMLInputElement>) => {
		const { name, value } = e.currentTarget;
		changeCredentials({
			...credentials,
			[name]: value,
		});
	};

	return (
		<div className="w-full h-full flex flex-col justify-center">
			<div className="w-8/12 mx-auto h-full flex flex-col justify-around rounded-md bg-content2 border-2 border-primary-400">
				<h1 className="text-3xl ">Register</h1>

				<div className="h-5/6 flex flex-row justify-around">
					<ScrollShadow className="w-7/12 h-full my-auto pt-10 flex flex-col gap-5" hideScrollBar size={5}>
						<div className="flex flex-row gap-5 justify-center">
							<Input
								name="firstName"
								label="Name"
								isRequired
								labelPlacement="outside"
								color="secondary"
								className="w-5/12"
								onChange={handleInput}
								value={credentials.firstName}
								isClearable
								onClear={() => {
									changeCredentials({
										...credentials,
										firstName: "",
									});
								}}
							/>

							<Input
								name="lastName"
								label="Surname"
								isRequired
								labelPlacement="outside"
								color="secondary"
								className="w-5/12"
								onChange={handleInput}
								value={credentials.lastName}
								isClearable
								onClear={() => {
									changeCredentials({
										...credentials,
										lastName: "",
									});
								}}
							/>
						</div>

						<div className="flex flex-row gap-5 justify-center">
							<Input
								name="userName"
								label="Username"
								isRequired
								labelPlacement="outside"
								color="secondary"
								className="w-5/12"
								onChange={handleInput}
								value={credentials.userName}
								isClearable
								onClear={() => {
									changeCredentials({
										...credentials,
										userName: "",
									});
								}}
							/>
						</div>

						<div className="gap-5 justify-center">
							<div className="flex flex-row gap-5 justify-center mb-2">
								<Input
									name="email"
									label="E-mail"
									isRequired
									labelPlacement="outside"
									color="secondary"
									className="w-5/12"
									onChange={handleInput}
									value={credentials.email}
									isClearable
									onClear={() => {
										changeCredentials({
											...credentials,
											email: "",
										});
									}}
								/>

								<Input
									name="confirmEmail"
									label="Confirm E-mail"
									isRequired
									labelPlacement="outside"
									color="secondary"
									className="w-5/12"
									onChange={handleInput}
									value={credentials.confirmEmail}
									isClearable
									onClear={() => {
										changeCredentials({
											...credentials,
											confirmEmail: "",
										});
									}}
								/>
							</div>

							<div className="flex flex-row gap-5 justify-center">
								<Input
									name="password"
									label="Password"
									isRequired
									type="password"
									labelPlacement="outside"
									color="secondary"
									className="w-5/12"
									onChange={handleInput}
									value={credentials.password}
									isClearable
									onClear={() => {
										changeCredentials({
											...credentials,
											password: "",
										});
									}}
								/>

								<Input
									name="confirmPassword"
									label="Confirm Password"
									isRequired
									type="password"
									labelPlacement="outside"
									color="secondary"
									className="w-5/12"
									onChange={handleInput}
									value={credentials.confirmPassword}
									isClearable
									onClear={() => {
										changeCredentials({
											...credentials,
											confirmPassword: "",
										});
									}}
								/>
							</div>
						</div>

						<div className="flex flex-row gap-5 mt-5 justify-center">
							<Input
								name="birthday"
								label="Birthday"
								type="date"
								labelPlacement="outside-left"
								color="secondary"
								className="w-5/12"
								onChange={handleInput}
								value={credentials.birthday}
								isClearable
								onClear={() => {
									changeCredentials({
										...credentials,
										birthday: "",
									});
								}}
							/>
						</div>

						<Button
							name="registerBTN"
							className="w-20 mx-auto my-10"
							color="secondary"
							onPress={() => {
								dispatch({ type: "REGISTER", payload: credentials });
							}}>
							Register
						</Button>
					</ScrollShadow>
					<div className="w-4/12 h-4/5 my-auto flex flex-col justify-center">
						<img src="/logo.png" alt="Instabasic Logo" className="w-10/12 m-auto" />
					</div>
				</div>
			</div>
		</div>
	);
};

export default Register;
