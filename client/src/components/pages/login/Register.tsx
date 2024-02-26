import { Button, Input, ScrollShadow } from "@nextui-org/react";

const Register = () => {
	return (
		<div className="w-full h-full flex flex-col justify-center">
			<div className="w-8/12 mx-auto h-full flex flex-col justify-around rounded-md bg-secondary-200">
				<h1 className="text-3xl ">Register</h1>

				<div className="h-5/6 flex flex-row justify-around">
					<ScrollShadow className="w-7/12 h-full my-auto pt-10 flex flex-col gap-5" hideScrollBar size={5}>
						<div className="flex flex-row gap-5 justify-center">
							<Input label="Name" isRequired labelPlacement="outside" color="primary" className="w-5/12" />
							<Input label="Surname" isRequired labelPlacement="outside" color="primary" className="w-5/12" />
						</div>

						<div className="flex flex-row gap-5 justify-center">
							<Input label="Username" isRequired labelPlacement="outside" color="primary" className="w-5/12" />
						</div>

						<div className="gap-5 justify-center">
							<div className="flex flex-row gap-5 justify-center mb-2">
								<Input label="E-mail" isRequired labelPlacement="outside" color="primary" className="w-5/12" />
								<Input label="Confirm E-mail" isRequired labelPlacement="outside" color="primary" className="w-5/12" />
							</div>

							<div className="flex flex-row gap-5 justify-center">
								<Input label="Password" isRequired type="password" labelPlacement="outside" color="primary" className="w-5/12" />
								<Input
									label="Confirm Password"
									isRequired
									type="password"
									labelPlacement="outside"
									color="primary"
									className="w-5/12"
								/>
							</div>
						</div>

						<div className="flex flex-row gap-5 mt-5 justify-center">
							<Input label="Birthday" type="date" labelPlacement="outside-left" color="primary" className="w-5/12" />
						</div>

						<Button className="w-20 mx-auto my-10" color="primary">
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
