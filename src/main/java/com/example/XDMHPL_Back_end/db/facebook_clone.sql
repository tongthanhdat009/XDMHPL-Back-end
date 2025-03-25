DROP DATABASE IF EXISTS facebook_clone;
CREATE DATABASE facebook_clone;
USE `facebook_clone`;

CREATE TABLE `ChatBox`(
    `ChatBoxID` int NOT NULL AUTO_INCREMENT,
    `ImageURL` nvarchar(200) NULL,
    `ChatBoxName` nvarchar(50) NULL,
    `Mute` Tinyint NULL,
    `Block` Tinyint NULL,
    PRIMARY KEY (`ChatBoxID`)
);

CREATE TABLE `ChatBoxDetail`(
    `UserID` int NOT NULL,
    `ChatBoxID` int NOT NULL,
    PRIMARY KEY (`UserID`, `ChatBoxID`)
);

CREATE TABLE `Comments`(
    `CommentID` int NOT NULL AUTO_INCREMENT,
    `CreationDate` date NULL,
    `UserID` int NULL,
    `PostID` int NULL,
    PRIMARY KEY (`CommentID`)
);

CREATE TABLE `Followers`(
    `FollowerID` int NOT NULL AUTO_INCREMENT,
    `UserID` int NULL,
    PRIMARY KEY (`FollowerID`)
);

CREATE TABLE `Following`(
    `FollowingID` int NOT NULL AUTO_INCREMENT,
    `UserID` int NULL,
    PRIMARY KEY (`FollowingID`)
);

CREATE TABLE `Friends`(
    `FriendID` int NOT NULL AUTO_INCREMENT,
    `UserID` int NULL,
    PRIMARY KEY (`FriendID`)
);

CREATE TABLE `Likes`(
    `LikeID` int NOT NULL AUTO_INCREMENT,
    `UserID` int NULL,
    `PostID` int NULL,
    PRIMARY KEY (`LikeID`)
);

CREATE TABLE `Message`(
    `MessageID` int NOT NULL AUTO_INCREMENT,
    `Text` nvarchar(500) NULL,
    `Time` datetime NULL,
    `Seen` Tinyint NULL,
    `Display` Tinyint NULL,
    `ChatBoxID` int NULL,
    PRIMARY KEY (`MessageID`)
);

CREATE TABLE `MessageMedia`(
    `MessageMediaID` int NOT NULL AUTO_INCREMENT,
    `MediaType` nvarchar(50) NULL,
    `MediaURL` nvarchar(200) NULL,
    `MessageID` int NULL,
    PRIMARY KEY (`MessageMediaID`)
);

CREATE TABLE `Notification`(
    `NotificationID` int NOT NULL AUTO_INCREMENT,
    `UserID` int NULL,
    `SenderID` int NULL,
    `Type` ENUM('LIKE', 'COMMENT', 'MESSAGE', 'FRIEND_REQUEST', 'FOLLOW') NULL,
    `PostID` int NULL,
    `CommentID` int NULL,
    `MessageID` int NULL,
    `Content` TEXT NULL,
    `CreatedAt` datetime(3) NULL,
    `IsReadFlag` Tinyint NULL,
    PRIMARY KEY (`NotificationID`)
);

CREATE TABLE `Post`(
    `PostID` int NOT NULL AUTO_INCREMENT,
    `CreationDate` date NULL,
    `Type` nvarchar(50) NULL,
    `UserID` int NULL,
    `SharedByUserID` int NULL,
    `Content` nvarchar(200) NULL,
    `PriorityScore` int DEFAULT 0,
    PRIMARY KEY (`PostID`)
);

CREATE TABLE `PostMedia`(
    `PostMediaID` int NOT NULL AUTO_INCREMENT,
    `Type` nvarchar(50) NULL,
    `MediaURL` nvarchar(200) NULL,
    `PostID` int NULL,
    PRIMARY KEY (`PostMediaID`)
);

CREATE TABLE `Users`(
    `UserID` int NOT NULL AUTO_INCREMENT,
    `FullName` VARCHAR(50) NULL,
    `Username` VARCHAR(50) NULL,
    `Password` VARCHAR(50) NULL,
    `Email` VARCHAR(50) NULL,
    `AvatarURL` VARCHAR(200) NULL,
    `CoverPhotoUrl` VARCHAR(200) NULL,
    `SessionID` VARCHAR(255) NULL,
    PRIMARY KEY (`UserID`)
);
CREATE TABLE `Sessions` (
    `SessionID` VARCHAR(255) NOT NULL,
    `UserID` INT NOT NULL,
    `CreatedAt` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `ExpiresAt` DATETIME NULL,
    `DeviceInfo` VARCHAR(255) NULL,
    PRIMARY KEY (`SessionID`),
    FOREIGN KEY (`UserID`) REFERENCES `Users`(`UserID`) ON DELETE CASCADE
);
ALTER TABLE `ChatBoxDetail` ADD CONSTRAINT `FK_ChatBoxDetail_ChatBox` FOREIGN KEY (`ChatBoxID`) REFERENCES `ChatBox` (`ChatBoxID`) ON DELETE CASCADE;
ALTER TABLE `ChatBoxDetail` ADD CONSTRAINT `FK_ChatBoxDetail_Users` FOREIGN KEY (`UserID`) REFERENCES `Users` (`UserID`) ON DELETE CASCADE;
ALTER TABLE `Comments` ADD CONSTRAINT `FK_Comments_Post` FOREIGN KEY (`PostID`) REFERENCES `Post` (`PostID`) ON DELETE CASCADE;
ALTER TABLE `Comments` ADD CONSTRAINT `FK_Comments_Users` FOREIGN KEY (`UserID`) REFERENCES `Users` (`UserID`) ON DELETE SET NULL;
ALTER TABLE `Followers` ADD CONSTRAINT `FK_Followers_Users` FOREIGN KEY (`UserID`) REFERENCES `Users` (`UserID`) ON DELETE CASCADE;
ALTER TABLE `Following` ADD CONSTRAINT `FK_Following_Users` FOREIGN KEY (`UserID`) REFERENCES `Users` (`UserID`) ON DELETE CASCADE;
ALTER TABLE `Friends` ADD CONSTRAINT `FK_Friends_Users` FOREIGN KEY (`UserID`) REFERENCES `Users` (`UserID`) ON DELETE CASCADE;
ALTER TABLE `Likes` ADD CONSTRAINT `FK_Likes_Post` FOREIGN KEY (`PostID`) REFERENCES `Post` (`PostID`) ON DELETE CASCADE;
ALTER TABLE `Likes` ADD CONSTRAINT `FK_Likes_Users` FOREIGN KEY (`UserID`) REFERENCES `Users` (`UserID`) ON DELETE CASCADE;
ALTER TABLE `Message` ADD CONSTRAINT `FK_Message_ChatBox` FOREIGN KEY (`ChatBoxID`) REFERENCES `ChatBox` (`ChatBoxID`) ON DELETE CASCADE;
ALTER TABLE `MessageMedia` ADD CONSTRAINT `FK_MessageMedia_Message` FOREIGN KEY (`MessageID`) REFERENCES `Message` (`MessageID`) ON DELETE CASCADE;
ALTER TABLE `Notification` ADD CONSTRAINT `FK_Notification_Comments` FOREIGN KEY (`CommentID`) REFERENCES `Comments` (`CommentID`) ON DELETE SET NULL;
ALTER TABLE `Notification` ADD CONSTRAINT `FK_Notification_Message` FOREIGN KEY (`MessageID`) REFERENCES `Message` (`MessageID`) ON DELETE SET NULL;
ALTER TABLE `Notification` ADD CONSTRAINT `FK_Notification_Post` FOREIGN KEY (`PostID`) REFERENCES `Post` (`PostID`) ON DELETE SET NULL;
ALTER TABLE `Notification` ADD CONSTRAINT `FK_Notification_Sender` FOREIGN KEY (`SenderID`) REFERENCES `Users` (`UserID`) ON DELETE SET NULL;
ALTER TABLE `Notification` ADD CONSTRAINT `FK_Notification_Users` FOREIGN KEY (`UserID`) REFERENCES `Users` (`UserID`) ON DELETE CASCADE;
ALTER TABLE `Post` ADD CONSTRAINT `FK_Post_Users` FOREIGN KEY (`UserID`) REFERENCES `Users` (`UserID`) ON DELETE CASCADE;
ALTER TABLE `PostMedia` ADD CONSTRAINT `FK_PostMedia_Post` FOREIGN KEY (`PostID`) REFERENCES `Post` (`PostID`) ON DELETE CASCADE;

DELIMITER $$

-- Trigger cập nhật PriorityScore khi có lượt thích
CREATE TRIGGER trg_update_priority_likes
AFTER INSERT ON Likes
FOR EACH ROW
BEGIN
    UPDATE Post SET PriorityScore = PriorityScore + 1 WHERE PostID = NEW.PostID;
END $$

-- Trigger giảm PriorityScore khi lượt thích bị xóa
CREATE TRIGGER trg_decrease_priority_likes
AFTER DELETE ON Likes
FOR EACH ROW
BEGIN
    UPDATE Post SET PriorityScore = PriorityScore - 1 WHERE PostID = OLD.PostID;
END $$

-- Trigger cập nhật PriorityScore khi có bình luận
CREATE TRIGGER trg_update_priority_comments
AFTER INSERT ON Comments
FOR EACH ROW
BEGIN
    UPDATE Post SET PriorityScore = PriorityScore + 2 WHERE PostID = NEW.PostID;
END $$

-- Trigger giảm PriorityScore khi bình luận bị xóa
CREATE TRIGGER trg_decrease_priority_comments
AFTER DELETE ON Comments
FOR EACH ROW
BEGIN
    UPDATE Post SET PriorityScore = PriorityScore - 2 WHERE PostID = OLD.PostID;
END $$

-- Trigger cập nhật PriorityScore khi có bài viết chia sẻ
CREATE TRIGGER trg_update_priority_shares
AFTER INSERT ON Post
FOR EACH ROW
BEGIN
    IF NEW.SharedByUserID IS NOT NULL THEN
        UPDATE Post SET PriorityScore = PriorityScore + 3 WHERE PostID = NEW.SharedByUserID;
    END IF;
END $$

-- Trigger giảm PriorityScore khi bài viết chia sẻ bị xóa
CREATE TRIGGER trg_decrease_priority_shares
AFTER DELETE ON Post
FOR EACH ROW
BEGIN
    IF OLD.SharedByUserID IS NOT NULL THEN
        UPDATE Post SET PriorityScore = PriorityScore - 3 WHERE PostID = OLD.SharedByUserID;
    END IF;
END $$
-- Trigger cập nhật PriorityScore theo thời gian
CREATE TRIGGER trg_decay_priority
BEFORE UPDATE ON Post
FOR EACH ROW
BEGIN
    DECLARE hours_since_creation FLOAT;
    SET hours_since_creation = TIMESTAMPDIFF(HOUR, NEW.CreationDate, NOW());
    IF hours_since_creation > 0 THEN
        SET NEW.PriorityScore = NEW.PriorityScore / POWER(hours_since_creation, 1.25);
    END IF;
END $$
DELIMITER ;
