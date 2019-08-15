/*
SQLyog Community v13.1.2 (64 bit)
MySQL - 5.7.26 : Database - activiti6
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`activiti6` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `activiti6`;

/*Table structure for table `act_evt_log` */

DROP TABLE IF EXISTS `act_evt_log`;

CREATE TABLE `act_evt_log` (
  `LOG_NR_` bigint(20) NOT NULL AUTO_INCREMENT,
  `TYPE_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TIME_STAMP_` timestamp(3) NOT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DATA_` longblob,
  `LOCK_OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `LOCK_TIME_` timestamp(3) NULL DEFAULT NULL,
  `IS_PROCESSED_` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`LOG_NR_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_evt_log` */

/*Table structure for table `act_ge_bytearray` */

DROP TABLE IF EXISTS `act_ge_bytearray`;

CREATE TABLE `act_ge_bytearray` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DEPLOYMENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `BYTES_` longblob,
  `GENERATED_` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_FK_BYTEARR_DEPL` (`DEPLOYMENT_ID_`),
  CONSTRAINT `ACT_FK_BYTEARR_DEPL` FOREIGN KEY (`DEPLOYMENT_ID_`) REFERENCES `act_re_deployment` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_ge_bytearray` */

insert  into `act_ge_bytearray`(`ID_`,`REV_`,`NAME_`,`DEPLOYMENT_ID_`,`BYTES_`,`GENERATED_`) values 
('10',1,'123456.process.png','8','âPNG\r\n\Z\n\0\0\0\rIHDR\0\0N\0\0\0˜\0\0\0Î€a\0\0\nÅIDATx⁄Ì›MhUÈpßÃ≤–m•H€E7˝X¥–EB]Ã@\ZnæÃ≠ää¡Fù0©R™h6mË(Ç≈bK¿ÖP+µIL¸®\ZtÏ®£ƒÍê8j∏M‘™’ö®—”˜#ôâWcçÒ‹úﬂ¢πô{ÂûGüˇºÁºÁNõ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\\Iíº›””≥≥´´Î·æ}˚í∂∂65…’ﬁﬁû<xZ®j	\0)C”°CáíÅÅÅ‰ﬁΩ{Í\r’’´Wì‹\n!jñÆÄîä+MBSj¬”Ω∂∂∂n]	\0)Oœ	-È©úËJ\0H©xçç¿í™‡îËJ\0(Ò‡tÁf!È9˛˚‰l˚⁄«ø\'ÏN\0 8ç™€7˙í3kHNÌY˘πäﬂãè	<Ç¿îbÀπ-ÁØú.ü˝Ûò–4RWŒÓx\'Ä©≈ñs[Œ_%8uwn(\Zú‚cé≠‡0•ÿrnÀ˘´ß3mçEÉS|Ãqú\0¶[Œm9ú\'\0&p8™l«ÒÙF‹EW,8≈«S¡	 ì¡…ñs¡ÈYu·ps—‡sL\'ÄÃ\'[ŒßbuΩÔtr¶ıÉ±ΩæsL\'ÄÃ\'[ŒßÁUÔG”Ò{éß‡ê…‡dÀπ‡T¥ÜÜíG>{ö.|/>Êò\nN\0ôNvN	N≈N·û?¸€¢Ωs*Wpúßlß°°§p°#˘dÔÍ¢}1RÒg‚œZ}ú\02úl9ú∆ª dıIp»|p≤Â\\p\Z©Ò¨2=oı…1ú\0¶|p≤Â\\p\Z©ˇ74çîc,8L˘‡dÀπ‡§Ù\0”l97\'Ω¿Ñ\rG[Œ\'•7\0x—p¥Â\\púÙ\0/é∂úéÇìﬁ\0`ú√—ñs√Qp“\0ås8⁄rn8\nNz\0√—p‘z\0√—p‘z\0√—p‘Jp0ï‡§\'†§¥∂∂˛dÛÊÕª÷≠[W®≠≠ΩüœÁïóó\'≥gœNñ,YÚ`Ì⁄µÕÕÕ{:::~*8)¡IÈ\r ìöööV444‹®´´K∂lŸítuu%}}}…‡‡`≈Ø…â\'íÌ€∑\'ÒÁ÷¨YÛü\r6¨úî‡§\' 6n‹¯›U´Vı≠X±‚QE√√√…xù<y2YΩzu˛€´õ6m˙°‡§\'%8S94}∞p·¬áªwÔ~©¿ÙE{˜ÓM‚ÛÑÙ+¡I	NJp¶úÕõ7øøtÈ“GÁœüO&¬≈ãì¯|MMMøúî‡§\'` hhh®!Áaºfi\"≈Áãœ€ÿÿ¯û‡§Je8ñïï}µ™™ÍkzCo\0å±`¡ÇoŒü?ˇ˛D≠4=kÂ)>}}˝˜\'U\n√±≤≤Ú˚ÂÂÂrπ‹÷ızCo\0<UWWw>^”Ù:≈kûjkk/\nN™ÜcK”„-7F*®è+**™ıÜﬁ\02.Ñô™ÂÀóﬂø?y›VÆ\\9^oë‡§“>„i∫—¡iTıÜj,++˚ñﬁúÄ™ØØˇ4ﬁr`2ú>}:Y∂lŸ%¡Iï¬p,úF◊Œx\ZOoN@Fƒˇ´^¥h—ΩWπÌ¿ÀäwèßA“Ù>TTT¥Ö˙±‡îΩ·ÿÿÿ¯•≤≤≤ØÑ˙z¯˚ùÿ°?ﬂ	U9é‡{˘ÆﬁúÄåX≤d…Í≠[∑>L&—é;í÷~ô¶˜ad∆\0ïÀÂ~$8ï∆p|^Ë	«qQ®˜¬Ø◊á˙0‘ü¬Ô˜ÑØıI®K°nÖ\Zu3‘g·Ò”·Î°P	ı«˚°\"ÅÈnx≠¶ô3g~#ææﬁúÄåX∂lŸ—¯1*ì)û¨≠≠˝(ç¡iT˝ıã+PÜc∫™µµud≈Áô°\'‘Ô¬1¸uO1D=YAz\'◊≤bÿä°\'⁄∑û”ΩENœM◊ÇêAsÁŒÌ/\nì\Zú‚}ùÚ˘¸øSúûÆ@ç(√1ï√Ò≠◊‹FıCgºEÅ’H¡	»∞ÍÍÍ{#ÿ;Y‚ÎÖÙ†Ç”Ë(√1ìáwÜ∫úÀÂñŒò1„Ìb?ß7\' #b(x∆TRWÜcˆÜcEE≈œÀ  æ¸¢ü”Çêùß°7¥‚tøDVúéÁrπw„jÉ·h8\nNz»∏9sÊﬁƒ5N555◊Rúû&√—púÙ¿c˘|~˜—£Gá\'{W]x›÷îß£ÒÙÃ≥Æg1\rG¡Io\0/zmnnæ>ô¡i€∂m7B@Yë≤‡4fÖ…p4\'Ω9ÒŒ·ÛÊÕª1ôwü?˛ı¥›9‹p4ıÜﬁ\0óÍÍÍŒc«é›öå–tÚ‰…€·ı∫JÒ}2\rGΩ°7\0oπ^∏paa2Vù/^|%º^µ‡§\'%8•ûˆ∑¥¥\\|ù°i◊Æ]ó√Î~ﬁuDÇìúî‡§^ºÊ®™™ÍÍπsÁnøé–‘€€˚ﬂ¯¸!8}ªTﬂ#√—p‘z‡©\\.WûœÁˇ’ﬂﬂ?4ë°)>_xﬁ˛R=Eg8\ZézCo\0[y˙EMMM°ªª˚⁄DÑ¶ûûûkÒ˘‚\'‘ó˙{c8\ZézCo\0<sÂ©¢¢‚⁄ˆÌ€?~ï∆[ZZNUVVˆ«06ﬁ√—p‘z†ÿ ”Ùû⁄Ú˘¸ßGé9Û2ÅÈÿ±cˇú7oﬁôáJ˘ö&√—p‘z‡e‘¨†™´´˚\Zètvv˛„J088xÁ…ˆﬁ)\nóC∏:µ~˝˙C555ób`ä´L•∫{Œp4ıÜﬁ\0x%Ò„!@’Ö@¥3To®¡\'üÌø^µ;~åJ)ﬁ‹p4ıÜﬁ\0¿p4ıÜﬁ\0¿pTÇìú\0\'%8)¡	@pRÇìú\0\'e8Í\rΩÄ·h8Í\rΩÄ·h8Í\rΩÄ·h8Í\r%8éJpRÇÄ‡§\'%8NJpRz@pRÜ£ﬁ–\0éÜ£ﬁ–\0éÜ£ﬁ–\0éÜc*Ï€∑œ1IO\rÜﬁx‡_,\0¡I•48<x∞000‡∏§†˙˙˙ZBot˚@pR)\rN?€øˇÕ˛˛˛ªéÕõ[iä°©ΩΩ˝≥P≥¸ã 8©_«Üıª·œt<û&ä65Èﬂ˜n°	@pR.\0\0¡I	N\0 8)¡	\0≤Ãñs[ŒÄq≤Â‹ñs\0`úl9∑Â\0x	∂ú€r\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\00.ˇÛæIcµÅk\0\0\0\0IENDÆB`Ç',1),
('13',1,'source',NULL,'{\"id\":\"canvas\",\"resourceId\":\"canvas\",\"stencilset\":{\"namespace\":\"http://b3mn.org/stencilset/bpmn2.0#\"}}',NULL),
('15',1,'source',NULL,'{\"id\":\"canvas\",\"resourceId\":\"canvas\",\"stencilset\":{\"namespace\":\"http://b3mn.org/stencilset/bpmn2.0#\"}}',NULL),
('17',1,'source',NULL,'{\"id\":\"canvas\",\"resourceId\":\"canvas\",\"stencilset\":{\"namespace\":\"http://b3mn.org/stencilset/bpmn2.0#\"}}',NULL),
('19',1,'source',NULL,'{\"id\":\"canvas\",\"resourceId\":\"canvas\",\"stencilset\":{\"namespace\":\"http://b3mn.org/stencilset/bpmn2.0#\"}}',NULL),
('2',2,'source',NULL,'{\"resourceId\":\"1\",\"properties\":{\"process_id\":\"process\",\"name\":\"\",\"documentation\":\"\",\"process_author\":\"\",\"process_version\":\"\",\"process_namespace\":\"http://www.activiti.org/processdef\",\"executionlisteners\":\"\",\"eventlisteners\":\"\",\"signaldefinitions\":\"\",\"messagedefinitions\":\"\"},\"stencil\":{\"id\":\"BPMNDiagram\"},\"childShapes\":[{\"resourceId\":\"sid-0279BDBF-B082-4EAC-AF6C-4A7199C1C776\",\"properties\":{\"overrideid\":\"\",\"name\":\"\",\"documentation\":\"\",\"executionlisteners\":\"\",\"initiator\":\"\",\"formkeydefinition\":\"\",\"formproperties\":\"\"},\"stencil\":{\"id\":\"StartNoneEvent\"},\"childShapes\":[],\"outgoing\":[{\"resourceId\":\"sid-B262BB35-838E-40F1-B573-C012579A9847\"}],\"bounds\":{\"lowerRight\":{\"x\":300.5,\"y\":212},\"upperLeft\":{\"x\":270.5,\"y\":182}},\"dockers\":[]},{\"resourceId\":\"sid-FFCAA625-0092-4EBB-AF3A-6C79E85B5E1F\",\"properties\":{\"overrideid\":\"\",\"name\":\"\",\"documentation\":\"\",\"asynchronousdefinition\":\"false\",\"exclusivedefinition\":\"false\",\"executionlisteners\":\"\",\"multiinstance_type\":\"None\",\"multiinstance_cardinality\":\"\",\"multiinstance_collection\":\"\",\"multiinstance_variable\":\"\",\"multiinstance_condition\":\"\",\"isforcompensation\":\"false\",\"usertaskassignment\":{\"assignment\":{\"candidateUsers\":[{\"value\":\"${userId}\",\"$$hashKey\":\"0N8\"}],\"candidateGroups\":[{\"value\":\"${userId}\",\"$$hashKey\":\"0NA\"}],\"assignee\":\"${canditor}\"}},\"formkeydefinition\":\"\",\"duedatedefinition\":\"\",\"prioritydefinition\":\"\",\"formproperties\":\"\",\"tasklisteners\":\"\"},\"stencil\":{\"id\":\"UserTask\"},\"childShapes\":[],\"outgoing\":[{\"resourceId\":\"sid-60EEF8F5-7FC0-4B2E-8C2A-D9DBA83889BB\"}],\"bounds\":{\"lowerRight\":{\"x\":445.5,\"y\":237},\"upperLeft\":{\"x\":345.5,\"y\":157}},\"dockers\":[]},{\"resourceId\":\"sid-B262BB35-838E-40F1-B573-C012579A9847\",\"properties\":{\"overrideid\":\"\",\"name\":\"\",\"documentation\":\"\",\"conditionsequenceflow\":\"\",\"executionlisteners\":\"\",\"defaultflow\":\"false\"},\"stencil\":{\"id\":\"SequenceFlow\"},\"childShapes\":[],\"outgoing\":[{\"resourceId\":\"sid-FFCAA625-0092-4EBB-AF3A-6C79E85B5E1F\"}],\"bounds\":{\"lowerRight\":{\"x\":344.65625,\"y\":197},\"upperLeft\":{\"x\":301.109375,\"y\":197}},\"dockers\":[{\"x\":15,\"y\":15},{\"x\":50,\"y\":40}],\"target\":{\"resourceId\":\"sid-FFCAA625-0092-4EBB-AF3A-6C79E85B5E1F\"}},{\"resourceId\":\"sid-F6021017-4696-4115-9319-742B36597562\",\"properties\":{\"overrideid\":\"\",\"name\":\"\",\"documentation\":\"\",\"asynchronousdefinition\":\"false\",\"exclusivedefinition\":\"false\",\"executionlisteners\":\"\",\"multiinstance_type\":\"None\",\"multiinstance_cardinality\":\"\",\"multiinstance_collection\":\"\",\"multiinstance_variable\":\"\",\"multiinstance_condition\":\"\",\"isforcompensation\":\"false\",\"usertaskassignment\":{\"assignment\":{\"candidateUsers\":[{\"value\":\"${userId}\",\"$$hashKey\":\"1J5\"}],\"assignee\":\"${canditor}\"}},\"formkeydefinition\":\"\",\"duedatedefinition\":\"\",\"prioritydefinition\":\"\",\"formproperties\":\"\",\"tasklisteners\":\"\"},\"stencil\":{\"id\":\"UserTask\"},\"childShapes\":[],\"outgoing\":[],\"bounds\":{\"lowerRight\":{\"x\":580,\"y\":230},\"upperLeft\":{\"x\":480,\"y\":150}},\"dockers\":[]},{\"resourceId\":\"sid-60EEF8F5-7FC0-4B2E-8C2A-D9DBA83889BB\",\"properties\":{\"overrideid\":\"\",\"name\":\"\",\"documentation\":\"\",\"conditionsequenceflow\":\"\",\"executionlisteners\":\"\",\"defaultflow\":\"false\"},\"stencil\":{\"id\":\"SequenceFlow\"},\"childShapes\":[],\"outgoing\":[{\"resourceId\":\"sid-F6021017-4696-4115-9319-742B36597562\"}],\"bounds\":{\"lowerRight\":{\"x\":479.6146328256163,\"y\":194.37771323255996},\"upperLeft\":{\"x\":445.8853671743837,\"y\":192.62228676744004}},\"dockers\":[{\"x\":50,\"y\":40},{\"x\":50,\"y\":40}],\"target\":{\"resourceId\":\"sid-F6021017-4696-4115-9319-742B36597562\"}}],\"bounds\":{\"lowerRight\":{\"x\":1200,\"y\":1050},\"upperLeft\":{\"x\":0,\"y\":0}},\"stencilset\":{\"url\":\"stencilsets/bpmn2.0/bpmn2.0.json\",\"namespace\":\"http://b3mn.org/stencilset/bpmn2.0#\"},\"ssextensions\":[]}',NULL),
('21',1,'source',NULL,'{\"id\":\"canvas\",\"resourceId\":\"canvas\",\"stencilset\":{\"namespace\":\"http://b3mn.org/stencilset/bpmn2.0#\"}}',NULL),
('2502',1,'source',NULL,'{\"id\":\"canvas\",\"resourceId\":\"canvas\",\"stencilset\":{\"namespace\":\"http://b3mn.org/stencilset/bpmn2.0#\"}}',NULL),
('3',1,'source-extra',NULL,'âPNG\r\n\Z\n\0\0\0\rIHDR\0\0v\0\0\0\0\0ÃGãˆ\0\0\0 cHRM\0\0z&\0\0ÄÑ\0\0˙\0\0\0ÄË\0\0u0\0\0Í`\0\0:ò\0\0pú∫Q<\0\0\0gAMA\0\0±é|˚Qì\0\0\0sRGB\0ÆŒÈ\0\0\0bKGD\0ˇ\0ˇ\0ˇ†Ωßì\0\0\0	pHYs\0\0ƒ\0\0ƒï+\0\0XIDATx⁄Ì›	êTıù7=3Ls#ä†F‘¬€YÀ§§bíM∂‘,Zª∫—µk◊´åÂELX≠DM¢)£qŸ∏,FàZÎ¡#¨\'\\:(ef@gfòËπ∫˜ˇX‹2¬Lwœ¯˘T=^3›Ô˝˛Õºoˇﬂ{ˇE\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0Ù>J\0\0˘!ì…$ÀÀÀ–⁄⁄:k€∂m_Ÿ∫uk2ùN+ÃæÜùÇÇLII…ñ~˝˙Ω———q€©ßû˙G¡\0Ë1!–˝s]]›Ì©Tj@iii4tË–h‡¡Q\"ëPú}á„ñ£ÊÊÊ®¶¶¶Ω´´ÎΩÌ€∑œö6mZô`\0t´\r6<∫~˝˙oè7.:‡Ä‚&EÈAUUUQEE≈ˆL&3„Ù”O¨/≠[°ÊÄ‹Ü∫0}˚à#éàFé)‘eA‹:|¯‚Õõ7c÷¨YÔÃõ7ÔOÇ\0∞O‚›ØW∆°n»ê!üzæ≠uSTÛÓG5Ô=’ïˇ1⁄÷PïïQº}‘Ø_ø8‹ï‘‘‘ú5sÊÃßÊœüøπ/¨ó˜\0êÒâÒ1uÒÓ◊›Ö∫çÀˇ#j≠[•ª⁄£L∫3⁄VˇA¥aŸ‹(’¸ëvÉÅF|p∫∞∞Œæ≤NÇ\0‰@|ˆk|¢D|L›Æ‘Wæu∂o˝‘„q»´+_¢Ä›dÏÿ±√ãääˆŸg\'ıÖı)“§\0ŸWVVvN{{˚•mmm\'l€∂m®!-ˆ]|lZˇ˛˝[¬ÙNqqÒ=\'N¸Ø|^ﬁxHì¯Ï◊›S∑˝3zÂ‚ﬁ<∫œ®Q£j>˙Ë£≥√Õ•Ç\0{l˘ÚÂß§R©\r\r\rÖçz¡ÿ±c\ri—Mvi1∏ππyÚ¶Mõ&ø˙Í´ˇﬁ˘\'ùt“í|\\ﬁxú∫8ÿÌN¶´c˜øúÒE†;Ìøˇ˛Ü`˜µæ∞.˛í\0d…ä+˛)Ñéó√∆|\\Ò±UÒXeB]7m–B„c’‚∞|¬	\'Dc∆åªuÎ÷ÁWÆ\\yy>.o<¯pÍ…Ω–c¬ÏX¡Ä=u≠≠≠˜u‘Qâ<–êYªvÙ—G\'ZZZn[∂lŸ˜Ûm˘‚F°>æD}d/¶O@[¥h—Ñ¶¶¶ª\'LòP∏´≥3ôt¥•‚≈ËÉWÓà÷<w}Ù˛K?ç∂î/Ÿq$˚&>Î1”!‹˝¸Ö^8¶7-{qˇ˝v˚\\Qâû>;Äú0`¿C„∆ç+ﬁU®ãmZÛxT˚˛‚®c{˝é˚ùmÕQÌœE5Ô=™x›Ó∆è_,ÏMÀ=|‹‰®®ﬂ¿O=^X‹?\Z>˛T\rã`êmã/>6ì…|ywCZƒaÆ©j˘.ük™^•Z™±ƒıO$áÙ¶!-ç<\"\Z∏ˇWv˘¯‡QGiT;Äl+))πhÃò1˝v;§E”á;v≈Óéa-∫Oiiiø–ˆ¶eÓÍÿ˛©«:€Z4&Ç@.Ñ@7->Ûuw˛ÍqtÜµË6ÒÆL&3µ7,k˚÷⁄Wòh≠}ÔSœm›≤.⁄÷};~;Äln†€€1§E~4hP‹„Ú}9„]Ûñ˝˚éÎ¬ÓŒ∂∆ ®‚ç_G\r^˝Ã_æxP–É2ôLë!-Ú√Œv»€∆à{‡6˝ÈÈ=r{\"æ¥X¸Û-µÔE£æ|Vî‹1_¯œπ\0‰Œg\ri±„€∑a-æ0‚∏=\ruü˜ÏUæyØ\"ÿ‰Zˇa„£Aª8Û16`¯óv<œC‹∑∑åy»ˇTÄ‹)(HD£\'|+j}yÌßûs‰ﬂEâ¬~äÙ1a OÅ}¶« «∫⁄∑ÓÚq√Z\0Ç@/R_˘r¥q≈˝ª|n„ÚﬂÓ∏‘¿û≤+ :Rç—¶5OD≠ukw˚3Ò1WÒ•∆‚ÍK\'¸m‘o‡HÖ;Ä|“·Q›œÓÚ™ªüıü1πˇ°ßG˚çõº„∏<\0¡ «‚]Øõ◊=Ûπ/ÓΩã/åvƒ¡ß)$∞Kæˆd—æi3¨YÙÿdQº;5û\0zÇ;\0\0¡\0\0¡\0\0¡\0Ä]sÚ–c/^|∆˙ıÎP[[;iÛÊÕ#öõõã⁄⁄⁄\näãã£!CÜté=∫a¯·oy‰ëwû~˙ÈãT@∞ÚÃÌ∑ﬂ~Ucc„uO?˝Ù∞#é8\"ö:ujt–AE!ƒE…d2J•RQKKK—∆çGñïïùı¯„èüu›u◊µÑ∞˜ãkÆπÊFÏÄ˚Ÿœ~vt}}˝™´´«Ãò1£‡òcéâ\n?ısq∏ãßë#GF«|t¡D´V≠\Z¸˚ﬂˇ˛Ü´Æ∫Í““““≥¬¸M¯|ctW®ªÈ˝˜ﬂ_1yÚ‰nΩı÷Ç8∞Ì*‘ÌNÁÃôMô2eˇuÎ÷-ΩÌ∂€Ê®*Ä`dŸ=˜‹s}eeÂMW^ye‚õﬂ¸ÊÁ\nti⁄¥i—µ◊^õ®®®∏ˆˆ€oøSu; Kn∏·Ü+   ~t„ç7~¯·›Úö„«èè‚◊+//ˇ˛ÏŸ≥® ‰£È”ß_¶sŒ=˜‹A™Aæpå∞◊.æ¯‚/’‘‘‹zı’W\'‚„Â∫S¸zW]uUbŒú97_q≈∏„é;V©8yÊ«Ò?ÖÖÖù!‡≠7ü\n”íd2πÙ˛˚ÔO)Ç–´0‡gúqFqwı‘˝•∏ÁÓÏ≥œ.~Í©ßwVqÚx[z‚ŒÈ¶T*’:c∆å•ôLfQ\"ëxÆ™™™l…í%ù Ñ`‰≠À.ªlfaa·°Ò1q=)~˝Áü~lxøKÔ∫ÎÆ_´<Ω¿†Í¶Ñ˘ît:ïññ÷Ñ†∑$z·ˇÃs<¿áJÑ`‰ï‚‚‚ùwﬁyÖÒ`√=Ì¸Ûœ/ú;wÓµ·f÷É]|UAAAYuuıìz]ÿK•!‘Mè?N]]]Òg™-‹~∑™™JeÏÄ‹õ9sÊÿÌ€∑Qí\r\'Nå“Ètiÿ º`¡Çä,ØÓè√F9\Z=zÙ∂˛OÜê˜€ŒŒŒózË°VüÑæÎ‹sœ--,,LÜ∂⁄|ˇèÁ;ü¶í}x˘¯wè[±bEÚ¡»≠a√Ü]pÏ±«ÌÀ∞&ü◊…\'ü\\¸ +Ø¸c∏yS.÷9l‘ÑŸw√˛ªaΩ„c®û˜ˇ≥§§dâÂ{áÍÍÍh˘ÚÂQhª[B;ñÜá‚≥Y?÷ävﬁÙâvˇ≥y7zˇ∏„é;L´ ÿπˇ√QTÙ≠â\'fu∏§¯“dØø˛˙7rÏ˛¬†èw≠•R©∂È”ß?6¸Ÿ]õﬂñ.]\Z≈Ωd¡û°S¶8∞«=≥uüò«‚c‰⁄Bõ◊Ñœ¡}üc^”=…drA¸e‡¢ã. h; ÁZZZçœXÕ¶¯Z≥ÕÕÕáÊa9‚›jÁÖ\r¸y£Gènô1c∆Û·˛ΩzÚÚœ§Iì¢   ∏◊Ó⁄8î}2¨uuu}[Êuüg7{ıüÏ¬Á¢)Ã~¶π.,”\nv@ﬁioodı=utt	“Ÿ˘Zóá\r˘w¬ÕÔ§R©x£øæ∂∂÷&Oå3f«µâØπÊö[≥vK„ÄüNßv<&Çê◊:;;˚%ì…¨æg¸~a#‘wS/*’°oºÒF<üÕ√ñ0=˛/|¡5 Å`Ùé?EEm©T™$õ·.ÓÃŒL&3\'À´ª\'A2ñ+ñÔœ˛¶Ü˚ÎO<ÒƒC}b˙º•°≠ÁÜ/<ÙŒ!ÿΩNqqqCccciêµ˜liiâﬂ∑iﬁºy≥≥πÆ”ßOﬂm∞aÆsgòKÑy\"‹oÛÁ¬Ù@II…3Ò1v^x°‰˚∞D\"q‹Ô~˜ªï*Å`ÙZÈt˙≠äääØá`óµÒN6n‹˜Ä-œı™á÷ñc«f;C]‹CÛLòˆÔﬂˇπÊ\Z}Bæ8Ñ:;†◊K•Rãñ-[6y“§I√≥ıû´V≠jlkk[ú„UOÏuqò{8LO%ì…\'ù˝\nv@Ô˝√QTÙËä+~_)[Éøˆ⁄kÈùa*\'‚›¨a∂$ëH|<(±û9@∞zø¯\"Ê≥fÕZπ|˘Ú„O:È§!=˝~´V≠j›æ}˚∫\\N,vCAAAE2ô|Xœ ÿ}RWW◊/ÁŒù˚Î„è?~HO˜⁄˝Ê7øi\nÔ˜´\\¨gì?—⁄@oëP`o<¯‡Éè677Ø}¯·á+{Ú}}Ù—\Z\Z*´´´®:Ä`ÙêL&ÛO<Òƒ¿µk◊ˆ»ÿ]€BpåÀª»5X;†≈«ºuvv^vÀ-∑l›ºys[wæv¸z≥gœnMß”ó≈@∞≤`·¬Ö⁄⁄⁄~xı’W7¨Y≥fKwºfyy˘ñ¯ıR©‘œC®õØ \0Çê%,∏?ÑªÀoæ˘Êhﬁºy+„aPˆ!(æ}˝ı◊wµ∑∑_nﬂ™∫\0{ŒY±@∑à{Ó¶OüæÙôgûπÔÖ^¯‡íK.Iùr )GÌÈÔø˘Êõ∫˜ﬁ{;∂n›⁄\\PP˜!,⁄˝\n ÿπ≤súπ©!‡ùs◊]w]r˜›wÔwÿaáU~ı´_M~¯·£Få1,ôLL•R[ ÀÀÎ_|Ò≈Êwﬂ}w|{{˚ÊªskjjÊ;Q@∞Ú\'‡≈WàxxÊÃôc◊Æ]{Œö5k˛&‹ﬂ/Lx˜Ò|Cò˛\'˛Ÿ\r> ÿÏâ¯\naˆãù\0=Ã…\0\0Ç\0\0Ç\0\0Ç\0\0Ç\0Ä`\0Ä`\0Ä`\0}xúHd“È¥B‰Å–ïa÷\'Æx#ÿ@$ì…Õ---\në\Z\Z\Z ¬l•`\0Ïï‚‚‚óõöö\"‘‘‘4áŸ¡\0ÿ+©TÍß’’’ôLF1r´¢ææ˛ò0D∞\0ˆ ißùˆFuoWUU)Fïïï=⁄°jÍ‘©K;\0`Ø•R©Y€öõõ#\Z´ØØˇV:ùæºØ¨ì`\092m⁄¥5!T|{ıÍ’M¬]ˆC›;Ôº3±††‡{° ;\0˛™∞—Ë0§E~ÿŸy◊S¶LyÆ≥≥ÛúU´V5oÿ∞°ﬁ1w=Æ¢¨¨Ïæ∑ﬂ~˚‰p˚_B˝üÏK+\'ÿÙ‰ŸDbï!-ÚCkkk‹´ÛqŸ‚p◊’’urEE≈[ØΩˆZÌ∫uÎV444‘¯R–m°ærÀñ-O≠^Ω˙¡ó^zi[}}˝!·±©}-‘≈ä47@œ	Îg¬Â†°CáéVç‹\nA©.lÃÂÎÚ≈ªe„Ÿ‚≈ãè≠ÆÆ>;L_˜áÖ)©ıˆŸÅa™\r”{a∫≥Øú(!ÿdY&ìy§¶¶Ê“C9$ﬁ-´ 9TUU’ıÇ!-Œ<ÛÃx†‹ïZåΩaW,@o§;;;ﬂ‹∞a√F’»ùPˇ\rÔˆÂû\ZÏ\0≤ J!‰ä˛MMMç™ë}≠≠≠€*++Ñv¯W’†Ø+TÄû5˛¸∫.∏`EmmÌ◊á⁄ôL&®J÷B]C|∂i&ì˘ﬁôgû˘≤ä ÿ∞œÊÕõ∑˛¸Ûœß¶¶Ê‹ééé∑ˆ€oøÒéπÎYÎ◊ØuÌ⁄µÒ…˜≈≥aW¸U»¢EãMH$?”—#FåX=j‘®ë!‰≈è9Ûq•”ÈTssÛªõ6m⁄RWWwHWWWEºº/\r>Ç@äá¥(((8;‹¸ZòNåi—:£ˇ;õtIòq¢\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0@/˜øH¨f\"|)Èù\0\0\0\0IENDÆB`Ç',NULL),
('5',1,'123456.bpmn20.xml','4','<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<definitions xmlns=\"http://www.omg.org/spec/BPMN/20100524/MODEL\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:activiti=\"http://activiti.org/bpmn\" xmlns:bpmndi=\"http://www.omg.org/spec/BPMN/20100524/DI\" xmlns:omgdc=\"http://www.omg.org/spec/DD/20100524/DC\" xmlns:omgdi=\"http://www.omg.org/spec/DD/20100524/DI\" typeLanguage=\"http://www.w3.org/2001/XMLSchema\" expressionLanguage=\"http://www.w3.org/1999/XPath\" targetNamespace=\"http://www.activiti.org/processdef\">\n  <process id=\"process\" isExecutable=\"true\">\n    <startEvent id=\"sid-0279BDBF-B082-4EAC-AF6C-4A7199C1C776\"></startEvent>\n    <userTask id=\"sid-FFCAA625-0092-4EBB-AF3A-6C79E85B5E1F\" activiti:assignee=\"${canditor}\" activiti:candidateUsers=\"${userId}\" activiti:candidateGroups=\"${userId}\"></userTask>\n    <sequenceFlow id=\"sid-B262BB35-838E-40F1-B573-C012579A9847\" sourceRef=\"sid-0279BDBF-B082-4EAC-AF6C-4A7199C1C776\" targetRef=\"sid-FFCAA625-0092-4EBB-AF3A-6C79E85B5E1F\"></sequenceFlow>\n    <userTask id=\"sid-F6021017-4696-4115-9319-742B36597562\" activiti:assignee=\"${canditor}\" activiti:candidateUsers=\"${userId}\"></userTask>\n    <sequenceFlow id=\"sid-60EEF8F5-7FC0-4B2E-8C2A-D9DBA83889BB\" sourceRef=\"sid-FFCAA625-0092-4EBB-AF3A-6C79E85B5E1F\" targetRef=\"sid-F6021017-4696-4115-9319-742B36597562\"></sequenceFlow>\n  </process>\n  <bpmndi:BPMNDiagram id=\"BPMNDiagram_process\">\n    <bpmndi:BPMNPlane bpmnElement=\"process\" id=\"BPMNPlane_process\">\n      <bpmndi:BPMNShape bpmnElement=\"sid-0279BDBF-B082-4EAC-AF6C-4A7199C1C776\" id=\"BPMNShape_sid-0279BDBF-B082-4EAC-AF6C-4A7199C1C776\">\n        <omgdc:Bounds height=\"30.0\" width=\"30.0\" x=\"270.5\" y=\"182.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"sid-FFCAA625-0092-4EBB-AF3A-6C79E85B5E1F\" id=\"BPMNShape_sid-FFCAA625-0092-4EBB-AF3A-6C79E85B5E1F\">\n        <omgdc:Bounds height=\"80.0\" width=\"100.0\" x=\"345.5\" y=\"157.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"sid-F6021017-4696-4115-9319-742B36597562\" id=\"BPMNShape_sid-F6021017-4696-4115-9319-742B36597562\">\n        <omgdc:Bounds height=\"80.0\" width=\"100.0\" x=\"480.0\" y=\"150.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNEdge bpmnElement=\"sid-B262BB35-838E-40F1-B573-C012579A9847\" id=\"BPMNEdge_sid-B262BB35-838E-40F1-B573-C012579A9847\">\n        <omgdi:waypoint x=\"300.5\" y=\"197.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"345.5\" y=\"197.0\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"sid-60EEF8F5-7FC0-4B2E-8C2A-D9DBA83889BB\" id=\"BPMNEdge_sid-60EEF8F5-7FC0-4B2E-8C2A-D9DBA83889BB\">\n        <omgdi:waypoint x=\"445.5\" y=\"194.39776951672863\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"480.0\" y=\"192.60223048327137\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n    </bpmndi:BPMNPlane>\n  </bpmndi:BPMNDiagram>\n</definitions>',0),
('5002',1,'source',NULL,'{\"id\":\"canvas\",\"resourceId\":\"canvas\",\"stencilset\":{\"namespace\":\"http://b3mn.org/stencilset/bpmn2.0#\"}}',NULL),
('5004',1,'source',NULL,'{\"id\":\"canvas\",\"resourceId\":\"canvas\",\"stencilset\":{\"namespace\":\"http://b3mn.org/stencilset/bpmn2.0#\"}}',NULL),
('6',1,'123456.process.png','4','âPNG\r\n\Z\n\0\0\0\rIHDR\0\0N\0\0\0˜\0\0\0Î€a\0\0\nÅIDATx⁄Ì›MhUÈpßÃ≤–m•H€E7˝X¥–EB]Ã@\ZnæÃ≠ää¡Fù0©R™h6mË(Ç≈bK¿ÖP+µIL¸®\ZtÏ®£ƒÍê8j∏M‘™’ö®—”˜#ôâWcçÒ‹úﬂ¢πô{ÂûGüˇºÁºÁNõ\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\\Iíº›””≥≥´´Î·æ}˚í∂∂65…’ﬁﬁû<xZ®j	\0)C”°CáíÅÅÅ‰ﬁΩ{Í\r’’´Wì‹\n!jñÆÄîä+MBSj¬”Ω∂∂∂n]	\0)Oœ	-È©úËJ\0H©xçç¿í™‡îËJ\0(Ò‡tÁf!È9˛˚‰l˚⁄«ø\'ÏN\0 8ç™€7˙í3kHNÌY˘πäﬂãè	<Ç¿îbÀπ-ÁØú.ü˝Ûò–4RWŒÓx\'Ä©≈ñs[Œ_%8uwn(\Zú‚cé≠‡0•ÿrnÀ˘´ß3mçEÉS|Ãqú\0¶[Œm9ú\'\0&p8™l«ÒÙF‹EW,8≈«S¡	 ì¡…ñs¡ÈYu·ps—‡sL\'ÄÃ\'[ŒßbuΩÔtr¶ıÉ±ΩæsL\'ÄÃ\'[ŒßÁUÔG”Ò{éß‡ê…‡dÀπ‡T¥ÜÜíG>{ö.|/>Êò\nN\0ôNvN	N≈N·û?¸€¢Ωs*Wpúßlß°°§p°#˘dÔÍ¢}1RÒg‚œZ}ú\02úl9ú∆ª dıIp»|p≤Â\\p\Z©Ò¨2=oı…1ú\0¶|p≤Â\\p\Z©ˇ74çîc,8L˘‡dÀπ‡§Ù\0”l97\'Ω¿Ñ\rG[Œ\'•7\0x—p¥Â\\púÙ\0/é∂úéÇìﬁ\0`ú√—ñs√Qp“\0ås8⁄rn8\nNz\0√—p‘z\0√—p‘z\0√—p‘Jp0ï‡§\'†§¥∂∂˛dÛÊÕª÷≠[W®≠≠ΩüœÁïóó\'≥gœNñ,YÚ`Ì⁄µÕÕÕ{:::~*8)¡IÈ\r ìöööV444‹®´´K∂lŸítuu%}}}…‡‡`≈Ø…â\'íÌ€∑\'ÒÁ÷¨YÛü\r6¨úî‡§\' 6n‹¯›U´Vı≠X±‚QE√√√…xù<y2YΩzu˛€´õ6m˙°‡§\'%8S94}∞p·¬áªwÔ~©¿ÙE{˜ÓM‚ÛÑÙ+¡I	NJp¶úÕõ7øøtÈ“GÁœüO&¬≈ãì¯|MMMøúî‡§\'` hhh®!Áaºfi\"≈Áãœ€ÿÿ¯û‡§Je8ñïï}µ™™ÍkzCo\0å±`¡ÇoŒü?ˇ˛D≠4=kÂ)>}}˝˜\'U\n√±≤≤Ú˚ÂÂÂrπ‹÷ızCo\0<UWWw>^”Ù:≈kûjkk/\nN™ÜcK”„-7F*®è+**™ıÜﬁ\02.Ñô™ÂÀóﬂø?y›VÆ\\9^oë‡§“>„i∫—¡iTıÜj,++˚ñﬁúÄ™ØØˇ4ﬁr`2ú>}:Y∂lŸ%¡Iï¬p,úF◊Œx\ZOoN@Fƒˇ´^¥h—ΩWπÌ¿ÀäwèßA“Ù>TTT¥Ö˙±‡îΩ·ÿÿÿ¯•≤≤≤ØÑ˙z¯˚ùÿ°?ﬂ	U9é‡{˘ÆﬁúÄåX≤d…Í≠[∑>L&—é;í÷~ô¶˜ad∆\0ïÀÂ~$8ï∆p|^Ë	«qQ®˜¬Ø◊á˙0‘ü¬Ô˜ÑØıI®K°nÖ\Zu3‘g·Ò”·Î°P	ı«˚°\"ÅÈnx≠¶ô3g~#ææﬁúÄåX∂lŸ—¯1*ì)û¨≠≠˝(ç¡iT˝ıã+PÜc∫™µµud≈Áô°\'‘Ô¬1¸uO1D=YAz\'◊≤bÿä°\'⁄∑û”ΩENœM◊ÇêAsÁŒÌ/\nì\Zú‚}ùÚ˘¸øSúûÆ@ç(√1ï√Ò≠◊‹FıCgºEÅ’H¡	»∞ÍÍÍ{#ÿ;Y‚ÎÖÙ†Ç”Ë(√1ìáwÜ∫úÀÂñŒò1„Ìb?ß7\' #b(x∆TRWÜcˆÜcEE≈œÀ  æ¸¢ü”Çêùß°7¥‚tøDVúéÁrπw„jÉ·h8\nNz»∏9sÊﬁƒ5N555◊Rúû&√—púÙ¿c˘|~˜—£Gá\'{W]x›÷îß£ÒÙÃ≥Æg1\rG¡Io\0/zmnnæ>ô¡i€∂m7B@Yë≤‡4fÖ…p4\'Ω9ÒŒ·ÛÊÕª1ôwü?˛ı¥›9‹p4ıÜﬁ\0óÍÍÍŒc«é›öå–tÚ‰…€·ı∫JÒ}2\rGΩ°7\0oπ^∏paa2Vù/^|%º^µ‡§\'%8•ûˆ∑¥¥\\|ù°i◊Æ]ó√Î~ﬁuDÇìúî‡§^ºÊ®™™ÍÍπsÁnøé–‘€€˚ﬂ¯¸!8}ªTﬂ#√—p‘z‡©\\.WûœÁˇ’ﬂﬂ?4ë°)>_xﬁ˛R=Eg8\ZézCo\0[y˙EMMM°ªª˚⁄DÑ¶ûûûkÒ˘‚\'‘ó˙{c8\ZézCo\0<sÂ©¢¢‚⁄ˆÌ€?~ï∆[ZZNUVVˆ«06ﬁ√—p‘z†ÿ ”Ùû⁄Ú˘¸ßGé9Û2ÅÈÿ±cˇú7oﬁôáJ˘ö&√—p‘z‡e‘¨†™´´˚\Zètvv˛„J088xÁ…ˆﬁ)\nóC∏:µ~˝˙C555ób`ä´L•∫{Œp4ıÜﬁ\0x%Ò„!@’Ö@¥3To®¡\'üÌø^µ;~åJ)ﬁ‹p4ıÜﬁ\0¿p4ıÜﬁ\0¿pTÇìú\0\'%8)¡	@pRÇìú\0\'e8Í\rΩÄ·h8Í\rΩÄ·h8Í\rΩÄ·h8Í\r%8éJpRÇÄ‡§\'%8NJpRz@pRÜ£ﬁ–\0éÜ£ﬁ–\0éÜ£ﬁ–\0éÜc*Ï€∑œ1IO\rÜﬁx‡_,\0¡I•48<x∞000‡∏§†˙˙˙ZBot˚@pR)\rN?€øˇÕ˛˛˛ªéÕõ[iä°©ΩΩ˝≥P≥¸ã 8©_«Üıª·œt<û&ä65Èﬂ˜n°	@pR.\0\0¡I	N\0 8)¡	\0≤Ãñs[ŒÄq≤Â‹ñs\0`úl9∑Â\0x	∂ú€r\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\00.ˇÛæIcµÅk\0\0\0\0IENDÆB`Ç',1),
('9',1,'123456.bpmn20.xml','8','<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<definitions xmlns=\"http://www.omg.org/spec/BPMN/20100524/MODEL\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:activiti=\"http://activiti.org/bpmn\" xmlns:bpmndi=\"http://www.omg.org/spec/BPMN/20100524/DI\" xmlns:omgdc=\"http://www.omg.org/spec/DD/20100524/DC\" xmlns:omgdi=\"http://www.omg.org/spec/DD/20100524/DI\" typeLanguage=\"http://www.w3.org/2001/XMLSchema\" expressionLanguage=\"http://www.w3.org/1999/XPath\" targetNamespace=\"http://www.activiti.org/processdef\">\n  <process id=\"process\" isExecutable=\"true\">\n    <startEvent id=\"sid-0279BDBF-B082-4EAC-AF6C-4A7199C1C776\"></startEvent>\n    <userTask id=\"sid-FFCAA625-0092-4EBB-AF3A-6C79E85B5E1F\" activiti:assignee=\"${canditor}\" activiti:candidateUsers=\"${userId}\" activiti:candidateGroups=\"${userId}\"></userTask>\n    <sequenceFlow id=\"sid-B262BB35-838E-40F1-B573-C012579A9847\" sourceRef=\"sid-0279BDBF-B082-4EAC-AF6C-4A7199C1C776\" targetRef=\"sid-FFCAA625-0092-4EBB-AF3A-6C79E85B5E1F\"></sequenceFlow>\n    <userTask id=\"sid-F6021017-4696-4115-9319-742B36597562\" activiti:assignee=\"${canditor}\" activiti:candidateUsers=\"${userId}\"></userTask>\n    <sequenceFlow id=\"sid-60EEF8F5-7FC0-4B2E-8C2A-D9DBA83889BB\" sourceRef=\"sid-FFCAA625-0092-4EBB-AF3A-6C79E85B5E1F\" targetRef=\"sid-F6021017-4696-4115-9319-742B36597562\"></sequenceFlow>\n  </process>\n  <bpmndi:BPMNDiagram id=\"BPMNDiagram_process\">\n    <bpmndi:BPMNPlane bpmnElement=\"process\" id=\"BPMNPlane_process\">\n      <bpmndi:BPMNShape bpmnElement=\"sid-0279BDBF-B082-4EAC-AF6C-4A7199C1C776\" id=\"BPMNShape_sid-0279BDBF-B082-4EAC-AF6C-4A7199C1C776\">\n        <omgdc:Bounds height=\"30.0\" width=\"30.0\" x=\"270.5\" y=\"182.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"sid-FFCAA625-0092-4EBB-AF3A-6C79E85B5E1F\" id=\"BPMNShape_sid-FFCAA625-0092-4EBB-AF3A-6C79E85B5E1F\">\n        <omgdc:Bounds height=\"80.0\" width=\"100.0\" x=\"345.5\" y=\"157.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"sid-F6021017-4696-4115-9319-742B36597562\" id=\"BPMNShape_sid-F6021017-4696-4115-9319-742B36597562\">\n        <omgdc:Bounds height=\"80.0\" width=\"100.0\" x=\"480.0\" y=\"150.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNEdge bpmnElement=\"sid-B262BB35-838E-40F1-B573-C012579A9847\" id=\"BPMNEdge_sid-B262BB35-838E-40F1-B573-C012579A9847\">\n        <omgdi:waypoint x=\"300.5\" y=\"197.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"345.5\" y=\"197.0\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"sid-60EEF8F5-7FC0-4B2E-8C2A-D9DBA83889BB\" id=\"BPMNEdge_sid-60EEF8F5-7FC0-4B2E-8C2A-D9DBA83889BB\">\n        <omgdi:waypoint x=\"445.5\" y=\"194.39776951672863\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"480.0\" y=\"192.60223048327137\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n    </bpmndi:BPMNPlane>\n  </bpmndi:BPMNDiagram>\n</definitions>',0);

/*Table structure for table `act_ge_property` */

DROP TABLE IF EXISTS `act_ge_property`;

CREATE TABLE `act_ge_property` (
  `NAME_` varchar(64) COLLATE utf8_bin NOT NULL,
  `VALUE_` varchar(300) COLLATE utf8_bin DEFAULT NULL,
  `REV_` int(11) DEFAULT NULL,
  PRIMARY KEY (`NAME_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_ge_property` */

insert  into `act_ge_property`(`NAME_`,`VALUE_`,`REV_`) values 
('next.dbid','7501',4),
('schema.history','create(5.22.0.0)',1),
('schema.version','5.22.0.0',1);

/*Table structure for table `act_hi_actinst` */

DROP TABLE IF EXISTS `act_hi_actinst`;

CREATE TABLE `act_hi_actinst` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `ACT_ID_` varchar(255) COLLATE utf8_bin NOT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `CALL_PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACT_NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ACT_TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `ASSIGNEE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `START_TIME_` datetime(3) NOT NULL,
  `END_TIME_` datetime(3) DEFAULT NULL,
  `DURATION_` bigint(20) DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_ACT_INST_START` (`START_TIME_`),
  KEY `ACT_IDX_HI_ACT_INST_END` (`END_TIME_`),
  KEY `ACT_IDX_HI_ACT_INST_PROCINST` (`PROC_INST_ID_`,`ACT_ID_`),
  KEY `ACT_IDX_HI_ACT_INST_EXEC` (`EXECUTION_ID_`,`ACT_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_hi_actinst` */

/*Table structure for table `act_hi_attachment` */

DROP TABLE IF EXISTS `act_hi_attachment`;

CREATE TABLE `act_hi_attachment` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `URL_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `CONTENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TIME_` datetime(3) DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_hi_attachment` */

/*Table structure for table `act_hi_comment` */

DROP TABLE IF EXISTS `act_hi_comment`;

CREATE TABLE `act_hi_comment` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TIME_` datetime(3) NOT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACTION_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `MESSAGE_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `FULL_MSG_` longblob,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_hi_comment` */

/*Table structure for table `act_hi_detail` */

DROP TABLE IF EXISTS `act_hi_detail`;

CREATE TABLE `act_hi_detail` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACT_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin NOT NULL,
  `VAR_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `REV_` int(11) DEFAULT NULL,
  `TIME_` datetime(3) NOT NULL,
  `BYTEARRAY_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DOUBLE_` double DEFAULT NULL,
  `LONG_` bigint(20) DEFAULT NULL,
  `TEXT_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TEXT2_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_DETAIL_PROC_INST` (`PROC_INST_ID_`),
  KEY `ACT_IDX_HI_DETAIL_ACT_INST` (`ACT_INST_ID_`),
  KEY `ACT_IDX_HI_DETAIL_TIME` (`TIME_`),
  KEY `ACT_IDX_HI_DETAIL_NAME` (`NAME_`),
  KEY `ACT_IDX_HI_DETAIL_TASK_ID` (`TASK_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_hi_detail` */

/*Table structure for table `act_hi_identitylink` */

DROP TABLE IF EXISTS `act_hi_identitylink`;

CREATE TABLE `act_hi_identitylink` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `GROUP_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_IDENT_LNK_USER` (`USER_ID_`),
  KEY `ACT_IDX_HI_IDENT_LNK_TASK` (`TASK_ID_`),
  KEY `ACT_IDX_HI_IDENT_LNK_PROCINST` (`PROC_INST_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_hi_identitylink` */

/*Table structure for table `act_hi_procinst` */

DROP TABLE IF EXISTS `act_hi_procinst`;

CREATE TABLE `act_hi_procinst` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `BUSINESS_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `START_TIME_` datetime(3) NOT NULL,
  `END_TIME_` datetime(3) DEFAULT NULL,
  `DURATION_` bigint(20) DEFAULT NULL,
  `START_USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `START_ACT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `END_ACT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SUPER_PROCESS_INSTANCE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DELETE_REASON_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  UNIQUE KEY `PROC_INST_ID_` (`PROC_INST_ID_`),
  KEY `ACT_IDX_HI_PRO_INST_END` (`END_TIME_`),
  KEY `ACT_IDX_HI_PRO_I_BUSKEY` (`BUSINESS_KEY_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_hi_procinst` */

/*Table structure for table `act_hi_taskinst` */

DROP TABLE IF EXISTS `act_hi_taskinst`;

CREATE TABLE `act_hi_taskinst` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_DEF_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PARENT_TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ASSIGNEE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `START_TIME_` datetime(3) NOT NULL,
  `CLAIM_TIME_` datetime(3) DEFAULT NULL,
  `END_TIME_` datetime(3) DEFAULT NULL,
  `DURATION_` bigint(20) DEFAULT NULL,
  `DELETE_REASON_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `PRIORITY_` int(11) DEFAULT NULL,
  `DUE_DATE_` datetime(3) DEFAULT NULL,
  `FORM_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_TASK_INST_PROCINST` (`PROC_INST_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_hi_taskinst` */

/*Table structure for table `act_hi_varinst` */

DROP TABLE IF EXISTS `act_hi_varinst`;

CREATE TABLE `act_hi_varinst` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin NOT NULL,
  `VAR_TYPE_` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `REV_` int(11) DEFAULT NULL,
  `BYTEARRAY_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DOUBLE_` double DEFAULT NULL,
  `LONG_` bigint(20) DEFAULT NULL,
  `TEXT_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TEXT2_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_TIME_` datetime(3) DEFAULT NULL,
  `LAST_UPDATED_TIME_` datetime(3) DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_PROCVAR_PROC_INST` (`PROC_INST_ID_`),
  KEY `ACT_IDX_HI_PROCVAR_NAME_TYPE` (`NAME_`,`VAR_TYPE_`),
  KEY `ACT_IDX_HI_PROCVAR_TASK_ID` (`TASK_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_hi_varinst` */

/*Table structure for table `act_id_group` */

DROP TABLE IF EXISTS `act_id_group`;

CREATE TABLE `act_id_group` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_id_group` */

/*Table structure for table `act_id_info` */

DROP TABLE IF EXISTS `act_id_info`;

CREATE TABLE `act_id_info` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `USER_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `VALUE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PASSWORD_` longblob,
  `PARENT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_id_info` */

/*Table structure for table `act_id_membership` */

DROP TABLE IF EXISTS `act_id_membership`;

CREATE TABLE `act_id_membership` (
  `USER_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `GROUP_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`USER_ID_`,`GROUP_ID_`),
  KEY `ACT_FK_MEMB_GROUP` (`GROUP_ID_`),
  CONSTRAINT `ACT_FK_MEMB_GROUP` FOREIGN KEY (`GROUP_ID_`) REFERENCES `act_id_group` (`ID_`),
  CONSTRAINT `ACT_FK_MEMB_USER` FOREIGN KEY (`USER_ID_`) REFERENCES `act_id_user` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_id_membership` */

/*Table structure for table `act_id_user` */

DROP TABLE IF EXISTS `act_id_user`;

CREATE TABLE `act_id_user` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `FIRST_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `LAST_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `EMAIL_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PWD_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PICTURE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_id_user` */

/*Table structure for table `act_procdef_info` */

DROP TABLE IF EXISTS `act_procdef_info`;

CREATE TABLE `act_procdef_info` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `INFO_JSON_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  UNIQUE KEY `ACT_UNIQ_INFO_PROCDEF` (`PROC_DEF_ID_`),
  KEY `ACT_IDX_INFO_PROCDEF` (`PROC_DEF_ID_`),
  KEY `ACT_FK_INFO_JSON_BA` (`INFO_JSON_ID_`),
  CONSTRAINT `ACT_FK_INFO_JSON_BA` FOREIGN KEY (`INFO_JSON_ID_`) REFERENCES `act_ge_bytearray` (`ID_`),
  CONSTRAINT `ACT_FK_INFO_PROCDEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_procdef_info` */

/*Table structure for table `act_re_deployment` */

DROP TABLE IF EXISTS `act_re_deployment`;

CREATE TABLE `act_re_deployment` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `DEPLOY_TIME_` timestamp(3) NULL DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_re_deployment` */

insert  into `act_re_deployment`(`ID_`,`NAME_`,`CATEGORY_`,`TENANT_ID_`,`DEPLOY_TIME_`) values 
('4','activiti',NULL,'','2019-08-14 18:08:44.712'),
('8','activiti',NULL,'','2019-08-14 18:08:49.086');

/*Table structure for table `act_re_model` */

DROP TABLE IF EXISTS `act_re_model`;

CREATE TABLE `act_re_model` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_TIME_` timestamp(3) NULL DEFAULT NULL,
  `LAST_UPDATE_TIME_` timestamp(3) NULL DEFAULT NULL,
  `VERSION_` int(11) DEFAULT NULL,
  `META_INFO_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DEPLOYMENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EDITOR_SOURCE_VALUE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EDITOR_SOURCE_EXTRA_VALUE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_FK_MODEL_SOURCE` (`EDITOR_SOURCE_VALUE_ID_`),
  KEY `ACT_FK_MODEL_SOURCE_EXTRA` (`EDITOR_SOURCE_EXTRA_VALUE_ID_`),
  KEY `ACT_FK_MODEL_DEPLOYMENT` (`DEPLOYMENT_ID_`),
  CONSTRAINT `ACT_FK_MODEL_DEPLOYMENT` FOREIGN KEY (`DEPLOYMENT_ID_`) REFERENCES `act_re_deployment` (`ID_`),
  CONSTRAINT `ACT_FK_MODEL_SOURCE` FOREIGN KEY (`EDITOR_SOURCE_VALUE_ID_`) REFERENCES `act_ge_bytearray` (`ID_`),
  CONSTRAINT `ACT_FK_MODEL_SOURCE_EXTRA` FOREIGN KEY (`EDITOR_SOURCE_EXTRA_VALUE_ID_`) REFERENCES `act_ge_bytearray` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_re_model` */

insert  into `act_re_model`(`ID_`,`REV_`,`NAME_`,`KEY_`,`CATEGORY_`,`CREATE_TIME_`,`LAST_UPDATE_TIME_`,`VERSION_`,`META_INFO_`,`DEPLOYMENT_ID_`,`EDITOR_SOURCE_VALUE_ID_`,`EDITOR_SOURCE_EXTRA_VALUE_ID_`,`TENANT_ID_`) values 
('1',6,'activiti','123456',NULL,'2019-08-14 17:53:23.915','2019-08-14 18:08:49.441',1,'{\"name\":\"activiti\",\"description\":\"\",\"revision\":1}','8','2','3',''),
('12',2,'activiti','123456',NULL,'2019-08-14 18:16:27.574','2019-08-14 18:16:27.611',1,'{\"name\":\"activiti\",\"description\":\"\",\"revision\":1}',NULL,'13',NULL,''),
('14',2,'activiti','123456',NULL,'2019-08-14 18:16:40.291','2019-08-14 18:16:40.350',1,'{\"name\":\"activiti\",\"description\":\"\",\"revision\":1}',NULL,'15',NULL,''),
('16',2,'activiti','123456',NULL,'2019-08-14 18:16:42.587','2019-08-14 18:16:42.607',1,'{\"name\":\"activiti\",\"description\":\"\",\"revision\":1}',NULL,'17',NULL,''),
('18',2,'activiti','123456',NULL,'2019-08-14 18:16:44.811','2019-08-14 18:16:44.831',1,'{\"name\":\"activiti\",\"description\":\"\",\"revision\":1}',NULL,'19',NULL,''),
('20',2,'activiti','123456',NULL,'2019-08-14 18:16:55.696','2019-08-14 18:16:55.717',1,'{\"name\":\"activiti\",\"description\":\"\",\"revision\":1}',NULL,'21',NULL,''),
('2501',2,'activiti','123456',NULL,'2019-08-14 19:05:02.602','2019-08-14 19:05:02.652',1,'{\"name\":\"activiti\",\"description\":\"\",\"revision\":1}',NULL,'2502',NULL,''),
('5001',2,'activiti','123456',NULL,'2019-08-14 19:08:15.028','2019-08-14 19:08:31.121',1,'{\"name\":\"activiti\",\"description\":\"\",\"revision\":1}',NULL,'5002',NULL,''),
('5003',2,'activiti','123456',NULL,'2019-08-14 19:14:09.130','2019-08-14 19:14:09.154',1,'{\"name\":\"activiti\",\"description\":\"\",\"revision\":1}',NULL,'5004',NULL,'');

/*Table structure for table `act_re_procdef` */

DROP TABLE IF EXISTS `act_re_procdef`;

CREATE TABLE `act_re_procdef` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `KEY_` varchar(255) COLLATE utf8_bin NOT NULL,
  `VERSION_` int(11) NOT NULL,
  `DEPLOYMENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `RESOURCE_NAME_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DGRM_RESOURCE_NAME_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `HAS_START_FORM_KEY_` tinyint(4) DEFAULT NULL,
  `HAS_GRAPHICAL_NOTATION_` tinyint(4) DEFAULT NULL,
  `SUSPENSION_STATE_` int(11) DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  UNIQUE KEY `ACT_UNIQ_PROCDEF` (`KEY_`,`VERSION_`,`TENANT_ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_re_procdef` */

insert  into `act_re_procdef`(`ID_`,`REV_`,`CATEGORY_`,`NAME_`,`KEY_`,`VERSION_`,`DEPLOYMENT_ID_`,`RESOURCE_NAME_`,`DGRM_RESOURCE_NAME_`,`DESCRIPTION_`,`HAS_START_FORM_KEY_`,`HAS_GRAPHICAL_NOTATION_`,`SUSPENSION_STATE_`,`TENANT_ID_`) values 
('process:1:7',1,'http://www.activiti.org/processdef',NULL,'process',1,'4','123456.bpmn20.xml','123456.process.png',NULL,0,1,1,''),
('process:2:11',1,'http://www.activiti.org/processdef',NULL,'process',2,'8','123456.bpmn20.xml','123456.process.png',NULL,0,1,1,'');

/*Table structure for table `act_ru_event_subscr` */

DROP TABLE IF EXISTS `act_ru_event_subscr`;

CREATE TABLE `act_ru_event_subscr` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `EVENT_TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `EVENT_NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACTIVITY_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `CONFIGURATION_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CREATED_` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_EVENT_SUBSCR_CONFIG_` (`CONFIGURATION_`),
  KEY `ACT_FK_EVENT_EXEC` (`EXECUTION_ID_`),
  CONSTRAINT `ACT_FK_EVENT_EXEC` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `act_ru_execution` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_ru_event_subscr` */

/*Table structure for table `act_ru_execution` */

DROP TABLE IF EXISTS `act_ru_execution`;

CREATE TABLE `act_ru_execution` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `BUSINESS_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PARENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `SUPER_EXEC_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `IS_ACTIVE_` tinyint(4) DEFAULT NULL,
  `IS_CONCURRENT_` tinyint(4) DEFAULT NULL,
  `IS_SCOPE_` tinyint(4) DEFAULT NULL,
  `IS_EVENT_SCOPE_` tinyint(4) DEFAULT NULL,
  `SUSPENSION_STATE_` int(11) DEFAULT NULL,
  `CACHED_ENT_STATE_` int(11) DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `LOCK_TIME_` timestamp(3) NULL DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_EXEC_BUSKEY` (`BUSINESS_KEY_`),
  KEY `ACT_FK_EXE_PROCINST` (`PROC_INST_ID_`),
  KEY `ACT_FK_EXE_PARENT` (`PARENT_ID_`),
  KEY `ACT_FK_EXE_SUPER` (`SUPER_EXEC_`),
  KEY `ACT_FK_EXE_PROCDEF` (`PROC_DEF_ID_`),
  CONSTRAINT `ACT_FK_EXE_PARENT` FOREIGN KEY (`PARENT_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_EXE_PROCDEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`),
  CONSTRAINT `ACT_FK_EXE_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `act_ru_execution` (`ID_`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ACT_FK_EXE_SUPER` FOREIGN KEY (`SUPER_EXEC_`) REFERENCES `act_ru_execution` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_ru_execution` */

/*Table structure for table `act_ru_identitylink` */

DROP TABLE IF EXISTS `act_ru_identitylink`;

CREATE TABLE `act_ru_identitylink` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `GROUP_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_IDENT_LNK_USER` (`USER_ID_`),
  KEY `ACT_IDX_IDENT_LNK_GROUP` (`GROUP_ID_`),
  KEY `ACT_IDX_ATHRZ_PROCEDEF` (`PROC_DEF_ID_`),
  KEY `ACT_FK_TSKASS_TASK` (`TASK_ID_`),
  KEY `ACT_FK_IDL_PROCINST` (`PROC_INST_ID_`),
  CONSTRAINT `ACT_FK_ATHRZ_PROCEDEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`),
  CONSTRAINT `ACT_FK_IDL_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_TSKASS_TASK` FOREIGN KEY (`TASK_ID_`) REFERENCES `act_ru_task` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_ru_identitylink` */

/*Table structure for table `act_ru_job` */

DROP TABLE IF EXISTS `act_ru_job`;

CREATE TABLE `act_ru_job` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `LOCK_EXP_TIME_` timestamp(3) NULL DEFAULT NULL,
  `LOCK_OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `EXCLUSIVE_` tinyint(1) DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROCESS_INSTANCE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `RETRIES_` int(11) DEFAULT NULL,
  `EXCEPTION_STACK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXCEPTION_MSG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DUEDATE_` timestamp(3) NULL DEFAULT NULL,
  `REPEAT_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_CFG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_FK_JOB_EXCEPTION` (`EXCEPTION_STACK_ID_`),
  CONSTRAINT `ACT_FK_JOB_EXCEPTION` FOREIGN KEY (`EXCEPTION_STACK_ID_`) REFERENCES `act_ge_bytearray` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_ru_job` */

/*Table structure for table `act_ru_task` */

DROP TABLE IF EXISTS `act_ru_task`;

CREATE TABLE `act_ru_task` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PARENT_TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TASK_DEF_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ASSIGNEE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DELEGATION_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PRIORITY_` int(11) DEFAULT NULL,
  `CREATE_TIME_` timestamp(3) NULL DEFAULT NULL,
  `DUE_DATE_` datetime(3) DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SUSPENSION_STATE_` int(11) DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `FORM_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_TASK_CREATE` (`CREATE_TIME_`),
  KEY `ACT_FK_TASK_EXE` (`EXECUTION_ID_`),
  KEY `ACT_FK_TASK_PROCINST` (`PROC_INST_ID_`),
  KEY `ACT_FK_TASK_PROCDEF` (`PROC_DEF_ID_`),
  CONSTRAINT `ACT_FK_TASK_EXE` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_TASK_PROCDEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`),
  CONSTRAINT `ACT_FK_TASK_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `act_ru_execution` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_ru_task` */

/*Table structure for table `act_ru_variable` */

DROP TABLE IF EXISTS `act_ru_variable`;

CREATE TABLE `act_ru_variable` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin NOT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `BYTEARRAY_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DOUBLE_` double DEFAULT NULL,
  `LONG_` bigint(20) DEFAULT NULL,
  `TEXT_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TEXT2_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_VARIABLE_TASK_ID` (`TASK_ID_`),
  KEY `ACT_FK_VAR_EXE` (`EXECUTION_ID_`),
  KEY `ACT_FK_VAR_PROCINST` (`PROC_INST_ID_`),
  KEY `ACT_FK_VAR_BYTEARRAY` (`BYTEARRAY_ID_`),
  CONSTRAINT `ACT_FK_VAR_BYTEARRAY` FOREIGN KEY (`BYTEARRAY_ID_`) REFERENCES `act_ge_bytearray` (`ID_`),
  CONSTRAINT `ACT_FK_VAR_EXE` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `ACT_FK_VAR_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `act_ru_execution` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_ru_variable` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
