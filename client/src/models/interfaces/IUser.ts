export interface IUser {
	id: string;
	username: string;
	email: string;
	password?: string;
	createdAt: string;
	updatedAt: string;
	lastLoginAt?: string;
	lastOnlineAt?: string;
}
