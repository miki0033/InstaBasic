import AddPostCard from "./cards/AddPostCard";
import PostCard from "./cards/PostCard";

export const ProfilePosts = ({ reloadImgs, profilePosts }: { reloadImgs: () => void; profilePosts: IPost[] }) => {
	return (
		<div className="w-full h-full px-14 pt-10 pb-5 flex flex-row flex-wrap justify-between gap-10">
			<AddPostCard signal={reloadImgs} key={1} />
			{profilePosts
				.slice(0)
				.reverse()
				.map((el, index) => {
					return (
						<>
							<PostCard post={el} key={el?.profile + index} />
						</>
					);
				})}
		</div>
	);
};
