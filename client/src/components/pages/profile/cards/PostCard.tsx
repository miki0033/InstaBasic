import { Modal, ModalContent, ModalBody, ModalFooter, Button, useDisclosure, Pagination, ModalHeader } from "@nextui-org/react";
import { Card, CardBody, CardFooter, Image } from "@nextui-org/react";
import { HeartIcon } from "@heroicons/react/24/outline";
import { useState } from "react";
import { useData } from "../../../main/DataProvider";
import PostComment from "../../../../utils/PostComment";

const PostCard = ({ post }: { post: IPost }) => {
	const { isOpen, onOpen, onOpenChange } = useDisclosure();
	const [modalImagePage, nextImage] = useState(0);
	const {
		state: { profile },
	} = useData();

	const GETIMAGE = import.meta.env.VITE_PYGET;

	return (
		<>
			<Card
				shadow="sm"
				isPressable
				onPress={() => {
					onOpen();
					nextImage(0);
				}}
				className="w-60 h-60 border border-secondary-400">
				<CardBody>
					<Image
						shadow="sm"
						radius="lg"
						width="100%"
						alt={post.title}
						className="w-full object-cover h-[140px]"
						src={GETIMAGE + post.imageUrl[0]}
					/>
				</CardBody>
				<CardFooter className="text-small">
					<p className="w-8/12">{post.title}</p>
					<Button className="w-4/12 text-default-500">
						{post.likes} <HeartIcon />
					</Button>
				</CardFooter>
			</Card>

			<Modal isOpen={isOpen} onOpenChange={onOpenChange} size="5xl" backdrop="blur" className="my-10">
				<ModalContent className=" border-2 border-secondary-400">
					{(onClose) => (
						<>
							<ModalHeader />
							<ModalBody className="p-0">
								<div className="w-full h-full flex flex-row flex-wrap">
									<div className="w-7/12 my-2 px-3 py-5 flex flex-col gap-10">
										<div className="mx-auto px-3">
											<Image
												alt={post.title}
												isBlurred
												className="max-h-[50vh] object-cover"
												src={GETIMAGE + post.imageUrl[modalImagePage]}
											/>
										</div>

										<Pagination
											className=" mx-auto"
											total={post.imageUrl.length}
											initialPage={1}
											onChange={(pages) => {
												nextImage(pages - 1);
											}}
										/>
									</div>

									<div className="w-5/12 my-10 px-5">
										<h1 className="py-3 text-center text-4xl ">{post.title}</h1>
										<PostComment
											avatar={profile?.avatarUrl ? GETIMAGE + profile.avatarUrl : ""}
											userName={profile?.profilename ? profile.profilename : ""}
											text={post.description}
										/>
									</div>
								</div>
							</ModalBody>
							<ModalFooter className="flex flex-row gap-5">
								<Button
									color="danger"
									onPress={() => {
										alert("WIP");
									}}>
									Delete Post
								</Button>
								<Button color="danger" variant="light" onPress={onClose}>
									Close
								</Button>
							</ModalFooter>
						</>
					)}
				</ModalContent>
			</Modal>
		</>
	);
};

export default PostCard;
