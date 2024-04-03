import { useEffect, useState } from "react";
import { useData } from "../../main/DataProvider";
import ProfileInfo from "./ProfileInfo";
import { ProfilePosts } from "./ProfilePosts";
import axios from "axios";

const Profile = () => {
	const {
		state: {
			profile: { profilename },
			token,
			type,
		},
	} = useData();

	const GET_POSTS = import.meta.env.VITE_GET_POSTS_BY_PROFILE_NAME;
	const [profilePosts, uploadPosts] = useState<IPost[]>([]);

	const getPosts = async () => {
		const config = {
			headers: { Authorization: type + " " + token },
		};
		const posts = await axios.get(GET_POSTS + profilename, config).then((res) => {
			return res.data.content;
		});
		return posts;
	};
	const reloadImgs = async () => {
		uploadPosts(await getPosts());
	};
	useEffect(() => {
		reloadImgs();
	}, []);

	return (
		<div className=" w-full h-full flex flex-row">
			<div className="w-4/12 bg-content2 border-2 border-secondary-400 border-r-divider rounded-l-md">
				<ProfileInfo nPosts={profilePosts.length} />
			</div>
			<div className="w-8/12 bg-content2 border-2 border-primary-400 border-l-divider rounded-r-md">
				<ProfilePosts reloadImgs={reloadImgs} profilePosts={profilePosts} />
			</div>
		</div>
	);
};

export default Profile;
