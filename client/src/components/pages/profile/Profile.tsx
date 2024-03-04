import ProfileInfo from "./ProfileInfo";
import { ProfilePosts } from "./ProfilePosts";

const Profile = () => {
	return (
		<div className=" w-full h-full flex flex-row">
			<div className="w-4/12 bg-content2 border-2 border-secondary-400 border-r-divider rounded-l-md">
				<ProfileInfo />
			</div>
			<div className="w-8/12 bg-content2 border-2 border-primary-400 border-l-divider rounded-r-md">
				<ProfilePosts />
			</div>
		</div>
	);
};

export default Profile;
