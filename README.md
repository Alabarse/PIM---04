# PIM---04
Sistema mobile simples e funcional para uma companhia de hotelaria, projeto acadêmico. Desenvolvido em java


Script para gerar o banco de dados: 

USE [master]
GO
/****** Object:  Database [Transilvania]    Script Date: 25/11/2021 17:21:34 ******/
CREATE DATABASE [Transilvania]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'Transilvania', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\Transilvania.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'Transilvania_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\Transilvania_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [Transilvania] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [Transilvania].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [Transilvania] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [Transilvania] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [Transilvania] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [Transilvania] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [Transilvania] SET ARITHABORT OFF 
GO
ALTER DATABASE [Transilvania] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [Transilvania] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [Transilvania] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [Transilvania] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [Transilvania] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [Transilvania] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [Transilvania] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [Transilvania] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [Transilvania] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [Transilvania] SET  ENABLE_BROKER 
GO
ALTER DATABASE [Transilvania] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [Transilvania] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [Transilvania] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [Transilvania] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [Transilvania] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [Transilvania] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [Transilvania] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [Transilvania] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [Transilvania] SET  MULTI_USER 
GO
ALTER DATABASE [Transilvania] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [Transilvania] SET DB_CHAINING OFF 
GO
ALTER DATABASE [Transilvania] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [Transilvania] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [Transilvania] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [Transilvania] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [Transilvania] SET QUERY_STORE = OFF
GO
USE [Transilvania]
GO
/****** Object:  Table [dbo].[login]    Script Date: 25/11/2021 17:21:34 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[login](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[usuario] [varchar](30) NOT NULL,
	[senha] [varchar](30) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
UNIQUE NONCLUSTERED 
(
	[usuario] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[produtos]    Script Date: 25/11/2021 17:21:34 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[produtos](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[quarto] [varchar](5) NOT NULL,
	[tipoProduto] [varchar](9) NOT NULL,
	[produto] [varchar](11) NOT NULL,
	[valor] [varchar](12) NOT NULL,
 CONSTRAINT [PK__produtos__3213E83FCD1F70DE] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[servicos]    Script Date: 25/11/2021 17:21:34 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[servicos](
	[quarto] [varchar](5) NOT NULL,
	[servico] [varchar](10) NOT NULL,
	[horario] [varchar](20) NOT NULL,
	[id] [int] IDENTITY(1,1) NOT NULL,
 CONSTRAINT [PK_servicos] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
USE [master]
GO
ALTER DATABASE [Transilvania] SET  READ_WRITE 
GO