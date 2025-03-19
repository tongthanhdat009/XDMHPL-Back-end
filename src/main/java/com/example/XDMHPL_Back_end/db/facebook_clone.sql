-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th3 19, 2025 lúc 04:17 AM
-- Phiên bản máy phục vụ: 10.4.32-MariaDB
-- Phiên bản PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `facebook_clone`
--

-- --------------------------------------------------------
DROP DATABASE IF EXISTS facebook_clone;  
CREATE DATABASE facebook_clone;          
USE facebook_clone;                      

--
-- Cấu trúc bảng cho bảng `chatbox`
--



CREATE TABLE `chatbox` (
  `ChatBoxID` int(11) NOT NULL,
  `ImageURL` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `ChatBoxName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Mute` tinyint(4) DEFAULT NULL,
  `Block` tinyint(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chatboxdetail`
--

CREATE TABLE `chatboxdetail` (
  `UserID` int(11) NOT NULL,
  `ChatBoxID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `comments`
--

CREATE TABLE `comments` (
  `CommentID` int(11) NOT NULL,
  `CreationDate` date DEFAULT NULL,
  `UserID` int(11) DEFAULT NULL,
  `PostID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `followers`
--

CREATE TABLE `followers` (
  `FollowerID` int(11) NOT NULL,
  `UserID` int(11) DEFAULT NULL,
  `FollowedUserID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `following`
--

CREATE TABLE `following` (
  `FollowingID` int(11) NOT NULL,
  `UserID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `friends`
--

CREATE TABLE `friends` (
  `FriendID` int(11) NOT NULL,
  `UserID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `like`
--

CREATE TABLE `like` (
  `LikeID` int(11) NOT NULL,
  `UserID` int(11) DEFAULT NULL,
  `PostID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Bẫy `like`
--
DELIMITER $$
CREATE TRIGGER `update_priority_after_like` AFTER INSERT ON `like` FOR EACH ROW BEGIN
    DECLARE follower_bonus INT DEFAULT 0;
    IF EXISTS (SELECT 1 FROM `Followers` WHERE `UserID` = NEW.`UserID` AND `FollowedUserID` = (SELECT `UserID` FROM `Post` WHERE `PostID` = NEW.`PostID`)) THEN
        SET follower_bonus = 2;
    END IF;
    
    UPDATE `Post`
    SET `PriorityScore` = ((SELECT COUNT(*) FROM `Like` WHERE `Like`.`PostID` = NEW.`PostID`) * 2 + 
                            (SELECT COUNT(*) FROM `Comments` WHERE `Comments`.`PostID` = NEW.`PostID`) * 3 + 
                            follower_bonus) 
                            / POW(TIMESTAMPDIFF(HOUR, `CreationDate`, NOW()), 1.5)
    WHERE `PostID` = NEW.`PostID`;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `message`
--

CREATE TABLE `message` (
  `MessageID` int(11) NOT NULL,
  `Text` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Time` date DEFAULT NULL,
  `Seen` tinyint(4) DEFAULT NULL,
  `Display` tinyint(4) DEFAULT NULL,
  `ChatBoxID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `messagemedia`
--

CREATE TABLE `messagemedia` (
  `MessageMediaID` int(11) NOT NULL,
  `MediaType` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `MediaURL` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `MessageID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `post`
--

CREATE TABLE `post` (
  `PostID` int(11) NOT NULL,
  `CreationDate` date DEFAULT NULL,
  `Type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `UserID` int(11) DEFAULT NULL,
  `SharedByUserID` int(11) DEFAULT NULL,
  `Content` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `PriorityScore` float DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `postmedia`
--

CREATE TABLE `postmedia` (
  `PostMediaID` int(11) NOT NULL,
  `Type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `MediaURL` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `PostID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `users`
--

CREATE TABLE `users` (
  `UserID` int(11) NOT NULL,
  `Password` varchar(255) DEFAULT NULL,
  `Email` varchar(255) NOT NULL,
  `Avatar` varchar(255) NOT NULL,
  `FullName` varchar(255) NOT NULL,
  `UserName` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `users`
--

INSERT INTO `users` (`UserID`, `Password`, `Email`, `Avatar`, `FullName`, `UserName`) VALUES
(1, 'password123', 'nguyenvana@example.com', 'avatar1.jpg', 'a', 'a'),
(2, 'password123', 'tranthib@example.com', 'avatar2.jpg', 'b', 'g'),
(3, 'password123', 'levanc@example.com', 'avatar3.jpg', 'c', 'x'),
(4, 'password123', 'phamthid@example.com', 'avatar4.jpg', 'd', 'd'),
(5, 'password123', 'hoangvane@example.com', 'avatar5.jpg', 'e', 'b'),
(6, '123456', 'a@gmail.com', 'abcs', 'dat', 'dat1');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `chatbox`
--
ALTER TABLE `chatbox`
  ADD PRIMARY KEY (`ChatBoxID`);

--
-- Chỉ mục cho bảng `chatboxdetail`
--
ALTER TABLE `chatboxdetail`
  ADD PRIMARY KEY (`UserID`,`ChatBoxID`),
  ADD KEY `FK_ChatBoxDetail_ChatBox` (`ChatBoxID`);

--
-- Chỉ mục cho bảng `comments`
--
ALTER TABLE `comments`
  ADD PRIMARY KEY (`CommentID`),
  ADD KEY `FK_Comments_Post` (`PostID`),
  ADD KEY `FK_Comments_Users` (`UserID`);

--
-- Chỉ mục cho bảng `followers`
--
ALTER TABLE `followers`
  ADD PRIMARY KEY (`FollowerID`),
  ADD KEY `FK_Followers_Users` (`UserID`);

--
-- Chỉ mục cho bảng `following`
--
ALTER TABLE `following`
  ADD PRIMARY KEY (`FollowingID`),
  ADD KEY `FK_Following_Users` (`UserID`);

--
-- Chỉ mục cho bảng `friends`
--
ALTER TABLE `friends`
  ADD PRIMARY KEY (`FriendID`),
  ADD KEY `FK_Friends_Users` (`UserID`);

--
-- Chỉ mục cho bảng `like`
--
ALTER TABLE `like`
  ADD PRIMARY KEY (`LikeID`),
  ADD KEY `FK_Like_Post` (`PostID`),
  ADD KEY `FK_Like_Users` (`UserID`);

--
-- Chỉ mục cho bảng `message`
--
ALTER TABLE `message`
  ADD PRIMARY KEY (`MessageID`),
  ADD KEY `FK_Message_ChatBox` (`ChatBoxID`);

--
-- Chỉ mục cho bảng `messagemedia`
--
ALTER TABLE `messagemedia`
  ADD PRIMARY KEY (`MessageMediaID`),
  ADD KEY `FK_MessageMedia_Message` (`MessageID`);

--
-- Chỉ mục cho bảng `post`
--
ALTER TABLE `post`
  ADD PRIMARY KEY (`PostID`),
  ADD KEY `FK_Post_Users` (`UserID`);

--
-- Chỉ mục cho bảng `postmedia`
--
ALTER TABLE `postmedia`
  ADD PRIMARY KEY (`PostMediaID`),
  ADD KEY `FK_PostMedia_Post` (`PostID`);

--
-- Chỉ mục cho bảng `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`UserID`),
  ADD UNIQUE KEY `Email` (`Email`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `chatbox`
--
ALTER TABLE `chatbox`
  MODIFY `ChatBoxID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `comments`
--
ALTER TABLE `comments`
  MODIFY `CommentID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `followers`
--
ALTER TABLE `followers`
  MODIFY `FollowerID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `following`
--
ALTER TABLE `following`
  MODIFY `FollowingID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `friends`
--
ALTER TABLE `friends`
  MODIFY `FriendID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `like`
--
ALTER TABLE `like`
  MODIFY `LikeID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `message`
--
ALTER TABLE `message`
  MODIFY `MessageID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `messagemedia`
--
ALTER TABLE `messagemedia`
  MODIFY `MessageMediaID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `post`
--
ALTER TABLE `post`
  MODIFY `PostID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `postmedia`
--
ALTER TABLE `postmedia`
  MODIFY `PostMediaID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `users`
--
ALTER TABLE `users`
  MODIFY `UserID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `chatboxdetail`
--
ALTER TABLE `chatboxdetail`
  ADD CONSTRAINT `FK_ChatBoxDetail_ChatBox` FOREIGN KEY (`ChatBoxID`) REFERENCES `chatbox` (`ChatBoxID`),
  ADD CONSTRAINT `FK_ChatBoxDetail_Users` FOREIGN KEY (`UserID`) REFERENCES `users` (`UserID`);

--
-- Các ràng buộc cho bảng `comments`
--
ALTER TABLE `comments`
  ADD CONSTRAINT `FK_Comments_Post` FOREIGN KEY (`PostID`) REFERENCES `post` (`PostID`),
  ADD CONSTRAINT `FK_Comments_Users` FOREIGN KEY (`UserID`) REFERENCES `users` (`UserID`);

--
-- Các ràng buộc cho bảng `followers`
--
ALTER TABLE `followers`
  ADD CONSTRAINT `FK_Followers_Users` FOREIGN KEY (`UserID`) REFERENCES `users` (`UserID`);

--
-- Các ràng buộc cho bảng `following`
--
ALTER TABLE `following`
  ADD CONSTRAINT `FK_Following_Users` FOREIGN KEY (`UserID`) REFERENCES `users` (`UserID`);

--
-- Các ràng buộc cho bảng `friends`
--
ALTER TABLE `friends`
  ADD CONSTRAINT `FK_Friends_Users` FOREIGN KEY (`UserID`) REFERENCES `users` (`UserID`);

--
-- Các ràng buộc cho bảng `like`
--
ALTER TABLE `like`
  ADD CONSTRAINT `FK_Like_Post` FOREIGN KEY (`PostID`) REFERENCES `post` (`PostID`),
  ADD CONSTRAINT `FK_Like_Users` FOREIGN KEY (`UserID`) REFERENCES `users` (`UserID`);

--
-- Các ràng buộc cho bảng `message`
--
ALTER TABLE `message`
  ADD CONSTRAINT `FK_Message_ChatBox` FOREIGN KEY (`ChatBoxID`) REFERENCES `chatbox` (`ChatBoxID`);

--
-- Các ràng buộc cho bảng `messagemedia`
--
ALTER TABLE `messagemedia`
  ADD CONSTRAINT `FK_MessageMedia_Message` FOREIGN KEY (`MessageID`) REFERENCES `message` (`MessageID`);

--
-- Các ràng buộc cho bảng `post`
--
ALTER TABLE `post`
  ADD CONSTRAINT `FK_Post_Users` FOREIGN KEY (`UserID`) REFERENCES `users` (`UserID`);

--
-- Các ràng buộc cho bảng `postmedia`
--
ALTER TABLE `postmedia`
  ADD CONSTRAINT `FK_PostMedia_Post` FOREIGN KEY (`PostID`) REFERENCES `post` (`PostID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
