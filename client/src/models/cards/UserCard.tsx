import { IUser } from "../interfaces/IUser";

const UserCard = ({ user }: { user: IUser }) => {
	return (
		<div className="border border-2 border-secondary w-5/12 h-20 px-4 py-2 rounded-md">
			<p>User ID: {user.id}</p>
			<p>E-mail: {user.email}</p>
		</div>
	);
};

export default UserCard;
