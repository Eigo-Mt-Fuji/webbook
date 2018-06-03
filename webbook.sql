/*
 * データベースへの
 * 各種テーブルの作成
 */

-- 利用者テーブルの作成
DROP TABLE IF EXISTS member CASCADE;
CREATE TABLE member (
	user_id SERIAL PRIMARY KEY,
	user_family_name VARCHAR(10) NOT NULL,
	user_name VARCHAR(10) NOT NULL,
	user_postal CHAR(7) NOT NULL,
	user_address VARCHAR(100) NOT NULL,
	user_tel VARCHAR(20) NOT NULL,
	user_email VARCHAR(100) NOT NULL,
	user_birthday DATE NOT NULL,
	user_password VARCHAR(12) DEFAULT 'himitu' NOT NULL,
	user_role CHAR(1) NOT NULL
);

-- 分類テーブルの作成
DROP TABLE IF EXISTS category CASCADE;
CREATE TABLE category (
	category_code CHAR(2) PRIMARY KEY,
	category_name VARCHAR(20) NOT NULL
);

-- 出版社テーブルの作成
DROP TABLE IF EXISTS publisher CASCADE;
CREATE TABLE publisher (
	publisher_code CHAR(2) PRIMARY KEY,
	publisher_name VARCHAR(20) NOT NULL
);

-- 共通図書情報テーブル作成
DROP TABLE IF EXISTS bookinfo CASCADE;
CREATE TABLE bookinfo (
	bookinfo_isbn CHAR(13) PRIMARY KEY,
	category_code CHAR(2) NOT NULL,
	publisher_code CHAR(2) NOT NULL,
	bookinfo_name VARCHAR(100) NOT NULL,
	bookinfo_author VARCHAR(20) NOT NULL
);

-- 固有図書情報テーブルの作成
DROP TABLE IF EXISTS bookstate CASCADE;
CREATE TABLE bookState (
	bookstate_id SERIAL PRIMARY KEY,
	bookinfo_isbn CHAR(13) NOT NULL
);

-- 貸出返却テーブルの作成
DROP TABLE IF EXISTS rental CASCADE;
CREATE TABLE rental (
	rental_id SERIAL NOT NULL,
	bookstate_id INTEGER NOT NULL ,
	user_id INTEGER NOT NULL,
	rental_rent DATE NOT NULL,
	rental_return DATE
);

-- 利用者テーブルのサンプルデータ
INSERT INTO member VALUES(1, '阿井', '太郎', '1000000', '東京都', '090-1111-1111', 'ai@dd.co.jp', '1984-10-01', DEFAULT, 1);
INSERT INTO member VALUES(2, '伊田', '次郎', '1100000', '千葉県', '090-2222-2222', 'ida@dd.co.jp', '1954-10-2', DEFAULT, 1);
INSERT INTO member VALUES(3, '宇田', '三郎', '1200000', '滋賀県', '090-3333-3333', 'uda@dd.co.jp', '1939-10-3', DEFAULT, 1);
INSERT INTO member VALUES(4, '江川', '四郎', '1300000', '佐賀県', '090-4444-4444', 'egawa@dd.co.jp', '1948-10-4', DEFAULT, 1);
INSERT INTO member VALUES(5, '岡本', '五郎', '1400000', '埼玉県', '090-5555-5555', 'okamoto@dd.co.jp', '1972-10-5', DEFAULT, 1);
INSERT INTO member VALUES(6, '甲斐', '太郎', '1500000', '徳島県', '090-6666-6666', 'kai@dd.co.jp', '1971-10-6', DEFAULT, 1);
INSERT INTO member VALUES(7, '木田', '次郎', '1600000', '群馬県', '090-7777-7777', 'kida@dd.co.jp', '1970-3-7', DEFAULT, 1);
INSERT INTO member VALUES(8, '草壁', '三郎', '1700000', '高知県', '090-8888-8888', 'kusakabe@dd.co.jp', '1966-5-8', DEFAULT, 1);
INSERT INTO member VALUES(9, '剣持', '四郎', '1800000', '秋田県', '090-9999-9999', 'kemmochi@dd.co.jp', '1961-10-9', DEFAULT, 1);
INSERT INTO member VALUES(10, '小室', '五郎', '1900000', '岩手県', '090-0000-0000', 'komuro@dd.co.jp', '1993-10-10', DEFAULT, 1);

-- 分類テーブルのサンプルデータ
INSERT INTO category VALUES('00', '文学・評論');
INSERT INTO category VALUES('01', '人文・思想');
INSERT INTO category VALUES('02', '社会・政治・法律');
INSERT INTO category VALUES('03', '歴史・地理');
INSERT INTO category VALUES('04', '科学・テクノロジー');
INSERT INTO category VALUES('05', '医学・薬学');
INSERT INTO category VALUES('06', 'コンピュータ・インターネット');
INSERT INTO category VALUES('07', '暮らし・健康・子育て');

-- 出版社テーブルのサンプルデータ
INSERT INTO publisher VALUES('00', 'A出版');
INSERT INTO publisher VALUES('01', 'B出版');
INSERT INTO publisher VALUES('02', 'C出版');
INSERT INTO publisher VALUES('03', 'D出版');
INSERT INTO publisher VALUES('04', 'E出版');
INSERT INTO publisher VALUES('05', 'F出版');

-- 共通図書情報テーブルのサンプルデータ
INSERT INTO bookinfo VALUES('kei000001', '00', '00', '税金はなぜ高いのか', '税博士');
INSERT INTO bookinfo VALUES('kei000002', '00', '00', '金融のからくり', '利惟哉');
INSERT INTO bookinfo VALUES('rek000001', '03', '00', '日本の歴史', '足利信長');
INSERT INTO bookinfo VALUES('rek000002', '03', '00', '米国の歴史', 'グッシュ');
INSERT INTO bookinfo VALUES('com000001', '06', '00', 'わかりやすいJava', '益田陽一');
INSERT INTO bookinfo VALUES('com000002', '06', '00', 'DBリファレンス', '戸塚信二');
INSERT INTO bookinfo VALUES('bun000001', '02', '00', '戦争と試合', 'トルトル');
INSERT INTO bookinfo VALUES('bun000002', '02', '00', '摘み賭罰', 'ドストアイスキー');
INSERT INTO bookinfo VALUES('sei000001', '07', '00', '猫と仲良くなるには', '猫田恵美');
INSERT INTO bookinfo VALUES('sei000002', '07', '00', 'らくちんダイエット', '細区奈留代');
