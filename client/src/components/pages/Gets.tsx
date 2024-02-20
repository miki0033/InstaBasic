import { Button } from "@nextui-org/react";
import { useState } from "react";
import UserCard from "../../models/cards/UserCard";
import { IUser } from "../../models/interfaces/IUser";

const Gets = () => {
	const [users, setUsers] = useState<IUser[]>();
	function getUsers() {
		fetch("http://localhost:3000/users")
			.then((data) => {
				return data.json();
			})
			.then((users) => {
				setUsers(users);
			});
	}

	return (
		<div className="flex flex-col py-10 gap-10">
			<div className="w-auto mx-auto py-4 px-6 flex gap-8 border border-2 border-primary rounded-lg">
				<Button
					onClick={() => {
						getUsers();
					}}>
					Users
				</Button>
			</div>

			<div className="w-3/4 h-[50vh] mx-auto py-3 border border-3 border-primary rounded-lg bg-default-100 flex flex-row flex-wrap justify-around overflow-auto">
				{users?.map((data) => {
					return <UserCard user={data} key={data.id} />;
				})}
			</div>
		</div>
	);
};

export default Gets;
