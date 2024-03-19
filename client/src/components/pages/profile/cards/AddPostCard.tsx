import {
	Modal,
	ModalContent,
	ModalBody,
	ModalFooter,
	Button,
	useDisclosure,
	ModalHeader,
	CardHeader,
	Input,
	Select,
	SelectItem,
} from "@nextui-org/react";
import { Card, CardBody, CardFooter } from "@nextui-org/react";
import { PlusCircleIcon } from "@heroicons/react/24/outline";
import { ChangeEvent, useState } from "react";
import axios from "axios";
import FormData from "form-data";

const AddPostCard = () => {
	const { isOpen, onOpen, onOpenChange } = useDisclosure();

	const [newPost, updateNewPost] = useState<IPost>({ title: "", description: "", url: [], type: "single" });
	const addUrlToPost = (newUrl: string) => {
		updateNewPost({
			...newPost,
			url: [...newPost.url, newUrl],
		});
	};
	const handleInput = (e: ChangeEvent<HTMLInputElement>) => {
		const { name, value } = e.currentTarget;
		updateNewPost({
			...newPost,
			[name]: value,
		});
	};
	const [postFiles, changeFiles] = useState<File[]>();

	const uploadPosts = () => {
		//first Upload img(s) to resource server
		const PYPOST_POST = import.meta.env.VITE_PYPOST_POST;
		postFiles?.forEach((file) => {
			let data = new FormData();
			data.append("file", file, file.name);

			axios
				.post(PYPOST_POST, data, {
					headers: {
						accept: "application/json",
						"Content-Type": `multipart/form-data; boundary=${data.getBoundary}`,
					},
				})
				.then((response): void => {
					const newUrl = response.data.imageURL;
					addUrlToPost(newUrl);
				});
		});
	};

	/*
	{
"title": "post title",
"description":"description",
"imageUrl":null,
"type":"post",
"profile":2
}*/

	return (
		<>
			<Card shadow="sm" isPressable onPress={onOpen} className="w-60 h-60 border border-secondary-400">
				<CardHeader>
					<p className="mx-auto my-auto">Add New Post</p>
				</CardHeader>
				<CardBody>
					<PlusCircleIcon />
				</CardBody>
				<CardFooter className=""></CardFooter>
			</Card>

			<Modal isOpen={isOpen} onOpenChange={onOpenChange} size="3xl" backdrop="blur" className="my-10" scrollBehavior="inside">
				<ModalContent className="border-2 border-secondary-400">
					{(onClose) => (
						<>
							<ModalHeader>Add Post</ModalHeader>
							<ModalBody className="text-center">
								<Input
									name="title"
									type="text"
									label="Post Title"
									labelPlacement="outside"
									className="w-1/3 mx-auto p-2"
									isRequired
									color="secondary"
									value={newPost.title}
									onChange={handleInput}
									isClearable
									onClear={() => {
										updateNewPost({
											...newPost,
											title: "",
										});
									}}
									//
								/>

								<Input
									name="description"
									type="text"
									label="Post Description"
									labelPlacement="outside"
									className="w-2/3 mx-auto p-2"
									isRequired
									color="secondary"
									value={newPost.description}
									onChange={handleInput}
									isClearable
									onClear={() => {
										updateNewPost({
											...newPost,
											description: "",
										});
									}}
									//
								/>

								<Select
									name="type"
									label="Select Post type: "
									labelPlacement="outside"
									className="w-1/3 mx-auto p-2"
									isRequired
									color="secondary"
									defaultSelectedKeys={[newPost.type]}
									onChange={(e: ChangeEvent<HTMLSelectElement>) => {
										updateNewPost({
											...newPost,
											type: e.target.value as "single" | "carousel",
										});
										changeFiles([]);
									}}
									//
								>
									<SelectItem key={"single"} value={"single"}>
										Single
									</SelectItem>

									<SelectItem key={"carousel"} value={"carousel"}>
										Carousel
									</SelectItem>
								</Select>

								<div className="flex flex-col">
									<label
										htmlFor="fileInput"
										className="w-auto h-1/6 mx-auto bg-secondary py-1 px-2 text-md text-secondary-200 rounded-md">
										{"Chose File(s)"}
									</label>
									<input
										id="fileInput"
										hidden
										name="files"
										type="file"
										multiple={newPost.type == "carousel"}
										onChange={(e: ChangeEvent<HTMLInputElement>) => {
											e.target.files?.length && changeFiles(Array.from(e.target.files));
										}}
									/>
									{postFiles?.map((fname) => {
										return (
											<h1 key={fname.name} className="text-secondary">
												{fname.name}
											</h1>
										);
									})}
								</div>
							</ModalBody>

							<ModalFooter className="flex flex-row gap-5">
								<Button color="primary" onPress={uploadPosts}>
									POST
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

export default AddPostCard;
