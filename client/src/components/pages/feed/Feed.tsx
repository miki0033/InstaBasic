import Carousel from "./Carousel";

const Feed = () => {
	return (
		<div className=" w-full h-full flex flex-row">
			<div className="w-1/5 bg-secondary-300 rounded-l-md">WIP</div>
			<div className="w-3/5 bg-primary-300 rounded-t-md">
				<div className="w-full h-28 bg-primary-400">
					<Carousel />
				</div>
				Feed
			</div>
			<div className="w-1/5 bg-secondary-300 rounded-r-md">Notification</div>
		</div>
	);
};

export default Feed;
