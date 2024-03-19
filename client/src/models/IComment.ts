interface IComment {
	id?: number;
	text: string;
	likes: number;
	createdAt: string;
	commenter: number;
	post: number;
}
