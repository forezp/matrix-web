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
('10',1,'123456.process.png','8','�PNG\r\n\Z\n\0\0\0\rIHDR\0\0N\0\0\0�\0\0\0��a\0\0\n�IDATx���MhU�p�̲�m�H�E7�X��EB]�@\Zn�̭���F�0�R�h6m�(��bK��P+�IL��\Zt쨣��8j�MԪ՚����#��Wc��ܜ����{�G�����N�\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0�\\I����ӳ�����}����65���ޞ<x�Z�j	\0)CӡC������޽{�\r�իW��\n!j�����+MBSj�ӽ���n]	\0)O�	-���J\0H�x��������J\0(��t�f!�9���l����\'�N\0 8���7��3kHN�Y���ߋ�	<���b˹-��.����4RW��x\'��Ŗs[�_%8uwn(\Z��c���0��rn����3m�E�S|�q�\0�[�m9�\'\0&p8�l���F�EW,8��S�	 ��ɖs��Yu�ps��sL\'��\'[��bu��tr������sL\'��\'[���U�G��{������d˹�T����G>{�.|/>�\nN\0�NvN	N�N�?�ۢ�s*Wp��l����p�#�d��}1R�g��Z}�\02�l9�ƻ�d�Ip�|p��\\p\Z��2=o��1�\0�|p��\\p\Z��74��c,8L��d˹��\0�l97\'���\rG[�\'�7\0x�p��\\p��\0/�������\0`��іs�Qp�\0�s8�rn8\nNz\0��p�z\0��p�z\0��p�Jp0��\'������d��ͻ֭[W����������\'�g�N�,Y�`�ڵ���{:::~*8)�I�\r ����V444ܨ��K�lْtuu%}}}���`ůɉ\'��۷\'��֬Y�\r6����\' 6n���U�V��X��QE����x�<y2Y�zu�۫�6m���\'%8S94}�p��w�~���E{��M����+�I	NJp��͛7��t��G�ϟO&�ŋ��|MMM����\'`�hhh�!�a�fi\"���������Je8���}����kzCo\0��`��oΟ?��D�4=k�)>}}��\'U\nñ�������r�����zCo\0<UWWw>^��:�k�jkk/\nN��cK��-7F*��+**����\02.����˗߿?y�V�\\9^o���>�i���iT��j,++���������4�r`2�>}:Y�l�%�I��p,�F��x\ZOoN@F���^�hѽW���ˊw��A��>TTT�������������������z�����?�	U9��{������X�d��[�>L&ю;��~���ad�\0���~$8��p|^�	�qQ��¯ׇ�0ԟ������I�K�n�\Zu3�g�����P	�����\"��nx���3g~#������X�l���1*�)�����(��iT���+P�c����ud�癡\'���1�uO1D=YAz\'��b؊�\'ڷ���EN�M���As���/\n�\Z��}�����S���@�(�1�����F�Cg�E��H�	Ȱ���{#�;Y�������(�1��w�����Θ1��b?�7\' #b(x�TRW�c��cEE����ʾ���������7��t�DV���r�w�j��h8\nNzȸ9s���5N555�R��&��p���c�|~�ѣG�\'{W]x�֔����̳�g1\rG�Io\0/zmnn�>��i۶m7B@Y���4f��p4\'��9�����ͻ1�w�?����9�p4���\0�����cǎݚ��t�������J�}2\rG��7\0o�^�paa2V�/^|%�^��\'%8������\\|��i׮]���~�uD������^�横���s�n����������!8}�T�#��p�z�\\.W�������?4��)>_x��R=Eg8\Z�zCo\0[y�EMMM�����D�����k���\'ԗ�{c8\Z�zCo\0<s婢������?~��[ZZNUVV��06���p�z�����������G�9�2��رc��7oޙ��J��&��p�z�eԬ�����\Z�tvv��J088x����)\n�C�:�~��C555�b`��L��{�p4���\0x%��!@Յ@�3To��\'���^�;~�J)��p4���\0�p4���\0�pT���\0\'%8)�	@pR���\0\'e8�\r���h8�\r���h8�\r���h8�\r%8�JpR���\'%8NJpRz@pR����\0�����\0�����\0��c*�۷�1IO\r��x�_,\0�I�48<x�000ฤ����ZBot�@pR)\rN?ۿ�������͛[i�������P��� 8�_������t<�&�65���n�	@pR.\0\0�I	N\0 8)�	\0�̖s[��q��ܖs\0`�l9��\0x	���r\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\00.��Ic��k\0\0\0\0IEND�B`�',1),
('13',1,'source',NULL,'{\"id\":\"canvas\",\"resourceId\":\"canvas\",\"stencilset\":{\"namespace\":\"http://b3mn.org/stencilset/bpmn2.0#\"}}',NULL),
('15',1,'source',NULL,'{\"id\":\"canvas\",\"resourceId\":\"canvas\",\"stencilset\":{\"namespace\":\"http://b3mn.org/stencilset/bpmn2.0#\"}}',NULL),
('17',1,'source',NULL,'{\"id\":\"canvas\",\"resourceId\":\"canvas\",\"stencilset\":{\"namespace\":\"http://b3mn.org/stencilset/bpmn2.0#\"}}',NULL),
('19',1,'source',NULL,'{\"id\":\"canvas\",\"resourceId\":\"canvas\",\"stencilset\":{\"namespace\":\"http://b3mn.org/stencilset/bpmn2.0#\"}}',NULL),
('2',2,'source',NULL,'{\"resourceId\":\"1\",\"properties\":{\"process_id\":\"process\",\"name\":\"\",\"documentation\":\"\",\"process_author\":\"\",\"process_version\":\"\",\"process_namespace\":\"http://www.activiti.org/processdef\",\"executionlisteners\":\"\",\"eventlisteners\":\"\",\"signaldefinitions\":\"\",\"messagedefinitions\":\"\"},\"stencil\":{\"id\":\"BPMNDiagram\"},\"childShapes\":[{\"resourceId\":\"sid-0279BDBF-B082-4EAC-AF6C-4A7199C1C776\",\"properties\":{\"overrideid\":\"\",\"name\":\"\",\"documentation\":\"\",\"executionlisteners\":\"\",\"initiator\":\"\",\"formkeydefinition\":\"\",\"formproperties\":\"\"},\"stencil\":{\"id\":\"StartNoneEvent\"},\"childShapes\":[],\"outgoing\":[{\"resourceId\":\"sid-B262BB35-838E-40F1-B573-C012579A9847\"}],\"bounds\":{\"lowerRight\":{\"x\":300.5,\"y\":212},\"upperLeft\":{\"x\":270.5,\"y\":182}},\"dockers\":[]},{\"resourceId\":\"sid-FFCAA625-0092-4EBB-AF3A-6C79E85B5E1F\",\"properties\":{\"overrideid\":\"\",\"name\":\"\",\"documentation\":\"\",\"asynchronousdefinition\":\"false\",\"exclusivedefinition\":\"false\",\"executionlisteners\":\"\",\"multiinstance_type\":\"None\",\"multiinstance_cardinality\":\"\",\"multiinstance_collection\":\"\",\"multiinstance_variable\":\"\",\"multiinstance_condition\":\"\",\"isforcompensation\":\"false\",\"usertaskassignment\":{\"assignment\":{\"candidateUsers\":[{\"value\":\"${userId}\",\"$$hashKey\":\"0N8\"}],\"candidateGroups\":[{\"value\":\"${userId}\",\"$$hashKey\":\"0NA\"}],\"assignee\":\"${canditor}\"}},\"formkeydefinition\":\"\",\"duedatedefinition\":\"\",\"prioritydefinition\":\"\",\"formproperties\":\"\",\"tasklisteners\":\"\"},\"stencil\":{\"id\":\"UserTask\"},\"childShapes\":[],\"outgoing\":[{\"resourceId\":\"sid-60EEF8F5-7FC0-4B2E-8C2A-D9DBA83889BB\"}],\"bounds\":{\"lowerRight\":{\"x\":445.5,\"y\":237},\"upperLeft\":{\"x\":345.5,\"y\":157}},\"dockers\":[]},{\"resourceId\":\"sid-B262BB35-838E-40F1-B573-C012579A9847\",\"properties\":{\"overrideid\":\"\",\"name\":\"\",\"documentation\":\"\",\"conditionsequenceflow\":\"\",\"executionlisteners\":\"\",\"defaultflow\":\"false\"},\"stencil\":{\"id\":\"SequenceFlow\"},\"childShapes\":[],\"outgoing\":[{\"resourceId\":\"sid-FFCAA625-0092-4EBB-AF3A-6C79E85B5E1F\"}],\"bounds\":{\"lowerRight\":{\"x\":344.65625,\"y\":197},\"upperLeft\":{\"x\":301.109375,\"y\":197}},\"dockers\":[{\"x\":15,\"y\":15},{\"x\":50,\"y\":40}],\"target\":{\"resourceId\":\"sid-FFCAA625-0092-4EBB-AF3A-6C79E85B5E1F\"}},{\"resourceId\":\"sid-F6021017-4696-4115-9319-742B36597562\",\"properties\":{\"overrideid\":\"\",\"name\":\"\",\"documentation\":\"\",\"asynchronousdefinition\":\"false\",\"exclusivedefinition\":\"false\",\"executionlisteners\":\"\",\"multiinstance_type\":\"None\",\"multiinstance_cardinality\":\"\",\"multiinstance_collection\":\"\",\"multiinstance_variable\":\"\",\"multiinstance_condition\":\"\",\"isforcompensation\":\"false\",\"usertaskassignment\":{\"assignment\":{\"candidateUsers\":[{\"value\":\"${userId}\",\"$$hashKey\":\"1J5\"}],\"assignee\":\"${canditor}\"}},\"formkeydefinition\":\"\",\"duedatedefinition\":\"\",\"prioritydefinition\":\"\",\"formproperties\":\"\",\"tasklisteners\":\"\"},\"stencil\":{\"id\":\"UserTask\"},\"childShapes\":[],\"outgoing\":[],\"bounds\":{\"lowerRight\":{\"x\":580,\"y\":230},\"upperLeft\":{\"x\":480,\"y\":150}},\"dockers\":[]},{\"resourceId\":\"sid-60EEF8F5-7FC0-4B2E-8C2A-D9DBA83889BB\",\"properties\":{\"overrideid\":\"\",\"name\":\"\",\"documentation\":\"\",\"conditionsequenceflow\":\"\",\"executionlisteners\":\"\",\"defaultflow\":\"false\"},\"stencil\":{\"id\":\"SequenceFlow\"},\"childShapes\":[],\"outgoing\":[{\"resourceId\":\"sid-F6021017-4696-4115-9319-742B36597562\"}],\"bounds\":{\"lowerRight\":{\"x\":479.6146328256163,\"y\":194.37771323255996},\"upperLeft\":{\"x\":445.8853671743837,\"y\":192.62228676744004}},\"dockers\":[{\"x\":50,\"y\":40},{\"x\":50,\"y\":40}],\"target\":{\"resourceId\":\"sid-F6021017-4696-4115-9319-742B36597562\"}}],\"bounds\":{\"lowerRight\":{\"x\":1200,\"y\":1050},\"upperLeft\":{\"x\":0,\"y\":0}},\"stencilset\":{\"url\":\"stencilsets/bpmn2.0/bpmn2.0.json\",\"namespace\":\"http://b3mn.org/stencilset/bpmn2.0#\"},\"ssextensions\":[]}',NULL),
('21',1,'source',NULL,'{\"id\":\"canvas\",\"resourceId\":\"canvas\",\"stencilset\":{\"namespace\":\"http://b3mn.org/stencilset/bpmn2.0#\"}}',NULL),
('2502',1,'source',NULL,'{\"id\":\"canvas\",\"resourceId\":\"canvas\",\"stencilset\":{\"namespace\":\"http://b3mn.org/stencilset/bpmn2.0#\"}}',NULL),
('3',1,'source-extra',NULL,'�PNG\r\n\Z\n\0\0\0\rIHDR\0\0v\0\0\0\0\0�G��\0\0\0 cHRM\0\0z&\0\0��\0\0�\0\0\0��\0\0u0\0\0�`\0\0:�\0\0p��Q<\0\0\0gAMA\0\0��|�Q�\0\0\0sRGB\0���\0\0\0bKGD\0�\0�\0�����\0\0\0	pHYs\0\0�\0\0��+\0\0XIDATx���	�T���7=3Ls#��F���Yˤ�b�M��,Z��ѵk׫��ELX�DM�)�qٸ,F�Z��#�\'\\:(ef@gf�蹺��X�2�Lw���T=^3����ͼo��{�E\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0�>J\0\0�!��$������:k۶m_ٺuk2�N+̾����LIIɖ~������q۩���G�\0�1!��s]]��Tj@iii4t��h���Q\"�P�}������樦�������۷Ϛ6mZ�`\0t�\r6<�~��o�7.:���&E�AUUUQEE��L&3���O�/�[���܆�0}��#��F�)�eA�:|���͛7c֬Y�̛7�O�\0�O�ݯWơnȐ!�z��uST��G5�=Օ�1��P��Q�}ԯ_�8ܕ��Ԝ5s�̧�ϟ��/���\0���1u���݅����#j�[��ڣL�3�V�A�a��(���v��F|p����ξ�N�\0�@|�k|�D|Lݮ�W�u�o���qȫ+_���d�رË����g\'���)Ҥ\0�WVVvN{{��mmm\'l۶m�!-�]|lZ���[��Nqq�=\'N��|^�xH�����S��3z���<�ϨQ�j>�裳�ͥ�\0{l��姤R�\r\r\r��z�رc\ri�Mvi1���y�M�&������\'�tҒ|\\�x��8��N��c����E�;����`����.��\0dɊ+�)�����|\\�U�XeB]7m�B�c��|�	\'Dcƌ�u���W�\\yy>.o<�p�ɽ�c��X��=u����u�Q�<АY�v��G\'ZZZn[�l���m��F�>�D}d/�O@[�hф����\'L�P���3�t�����W��<w}��K?���/�q$�&>�1�!����^8�7-{q��v�\\Q��>;��0`�C�ƍ+�U��mZ�xT���c{����m�Q��E5�=�x��Ə_,�M�=|�䨨��O=^X�?\Z>�T\r�`�m�/>6��|ywCZ�a��j�.�k�^�Z����O$���!-�<\"\Z��Wv���QGiT;�l+))�h̘1�v;�EӇ;v��a-�Oiii����e������:�Z4&�@.�@7->�uw��qt���6��L&3�7,k���W�h�}�S�mݲ.���};~;�ln���1�E~4hP���}9�]��������ζ�ʨ�_G\r^��_�xPЃ2�L�!-���v��ƈ{�6���=r{\"��X��-��E��|V��1_�Ϲ\0��g\ri��۷a-�0��=\ru���U�y�\"��Z�a�A�8�16`��v<�C����y��T��)(HD�\'|+j}y�s��E��~��1a�O�}�� Ǻڷ��q�Z\0�@/R_�r�q���|n��������+ :R�Ѧ5OD�ukw�3�1W����K\'�m�o�H�;�|���Q��������1����G�����<\0� ��]���=�//�v���)$�K��dѾi3��Y��dQ�;5�\0z�;\0\0�\0\0�\0\0�\0�]s��c/^|����P[[;i���#�������\n����!C�t�=�a���oy�w�~��T@�����~Ucc�uO?���#�8\"�:ujt�AE!�E�d2J�RQKKK�ƍG������㏟u�u׵����k���F����~vt}}�����̘1���c��\n?�sq����#GF�|t�D�V�\Z�������������ҳ��M�|ctW������_1y��n��ւ8��*��N�̙M�2e�u��-����*�`d�=��s}ee�MW^ye�����\ntiڴiѵ�^��������o�Su; Kn��+���~t�7~�����Ǐ���+//���ٳ����ӧ_�s�=��A�A�p���.���/����z��W\'���S�zW]uUbΜ97_q���;V�8y���?����!�7�\nӒd2�����O)�Ы0�g�qFqw���������.~ꩧwVq�x[z���T*�:cƌ��LfQ\"�x����lɒ%�ʄ`��.�lfaa��1q=)~��~lx�K��_�<���ꦄ��t:���ք��$z���s<���J�`�����w�y��`�=����/�;w��fփ]|UAAAYuu��z]�K�!�M�?N]]]�g�-�~���Je�ܛ9s���۷Q�\r\'N���ti� �`���,���F9\Z=z����O������Ηz�V�����s�--,,L���|���;���}x��w�[�bE��ȭaÆ]p���˰&���\'�\\��+��c�yS.�9l���w���a��c�������d��{����h���Qh�[B;����Y?֊v��v��y7z���;L� ����QT���\'fu����d����7r�� �w��R���ӧ?6��]�ߖ.]\ZŽd���S�8��=�u����c��B�ׄ��}�c^�=�drA�eࢋ.�h; �ZZZ��Xͦ�Z���͇�a9��j�\r�y�G�n�1c�����z��ϤI����ʸ���8�}2�uuu}[�u�g7{�����)�~��.,�\nv@�iood�=utt	���Z��\r�w���R�x������&O�3fǵ����[��vK〟N�v<&���:;;�%�ɬ�g�~a#�wS/*աo��F<��Ö0=�/|��5ʁ`�?EEm�T�$��.���L&3\'˫�\'A2�+��������O<��C}b������/<��!��NqqqCccci���lii�߷i޼y����ӧO�m�a�sg�K�y\"�o����@II�3�1v^x����D\"q��~���*�`�Z�t�������`���N6n���-�������c�f;C]�C�L�������\Z}B�8�:;��K�R��-[6yҤIó���V�jlkk[��UO�uq�{8LO%��\'��\nv@���QT��+~_)[����k�a*\'�ݬa�$�H|<(��9@�z��\"�f�Z�|���O:�!=�~�V�jݾ}��\\N,vCAAAE2�|X� �}RWW�/�Ν���?~HO����7�i\n���\\�g�?��@o�P`o<����677�}��+{�}}��\Z\Z*����:�`��L&�O<����k����]�Bp�˻�5X;��Ǽuvv^v�-�lݼys[w�v�z�g�nM�ӗ�@��`����~x��W7�Y�fKw�fyy����R���C����\0��%,�?���o���h޼y+�aP�!(�}���w���_nߪ�\0{�Y�@��{�O����g���^���K.I�r�)G��������{;�n��\\PP��!,��\n ���s���!��s�]w]r��w�w�a�U~��_M~��F�1,�LL�R[����_|���w�}w|{{���skjj�;Q@��\'��W�xx�̙c׮]{Κ5k�&��/Lx��|C��\'��\r> ���\na���\0=��\0\0�\0\0�\0\0�\0\0�\0�`\0�`\0�`\0}x�Hd��B���a�\'�x#�@$���---\n�\Z\Z\Z��l�`\0���◛��\"���4���\0�+�T�����LF1r������0D�\0��i���FuoWUU)F���=ڡj�ԩK;\0`��R�Yۚ��#\Z����V:������`\092mڴ5!T|{���M�]�C�;�3����{��;\0�����0�E~��y�S�Ly����U�V5oذ��1w=����쾷�~��p�_B���K+\'����Db�!-�Ckkk���q��p���urEE�[���Z�u�V444��R�m��r˖-O�^����^zi[}}�!ᱩ}-�Ŋ47@�	�g�堡C��V��\nA�.l����Że���ŋ����>;L_���)���فa�\r�{a����(!�dY&�y�����C9$�-� 9TUU���!-�<��x�ܕZ��aW,@o�;;;�ܰa�F�ȝP�\r���\Z�\0� J!��MMM���}����*++�v�Wՠ�+T��5���.�`Emm�ׇڙL&�J�B]C|�i&��ޙg���� ����͛���������܎�����o���Y�ׯu�ڵ���ųaW�UȢE�MH$?��#F�X=jԨ�!�ŏ9�q���Tss�6m�RWWwHWWWE��/\r>�@���(((8;��Z�N�i�:��;�tI�q�\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0@/��H�f\"|)�\0\0\0\0IEND�B`�',NULL),
('5',1,'123456.bpmn20.xml','4','<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<definitions xmlns=\"http://www.omg.org/spec/BPMN/20100524/MODEL\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:activiti=\"http://activiti.org/bpmn\" xmlns:bpmndi=\"http://www.omg.org/spec/BPMN/20100524/DI\" xmlns:omgdc=\"http://www.omg.org/spec/DD/20100524/DC\" xmlns:omgdi=\"http://www.omg.org/spec/DD/20100524/DI\" typeLanguage=\"http://www.w3.org/2001/XMLSchema\" expressionLanguage=\"http://www.w3.org/1999/XPath\" targetNamespace=\"http://www.activiti.org/processdef\">\n  <process id=\"process\" isExecutable=\"true\">\n    <startEvent id=\"sid-0279BDBF-B082-4EAC-AF6C-4A7199C1C776\"></startEvent>\n    <userTask id=\"sid-FFCAA625-0092-4EBB-AF3A-6C79E85B5E1F\" activiti:assignee=\"${canditor}\" activiti:candidateUsers=\"${userId}\" activiti:candidateGroups=\"${userId}\"></userTask>\n    <sequenceFlow id=\"sid-B262BB35-838E-40F1-B573-C012579A9847\" sourceRef=\"sid-0279BDBF-B082-4EAC-AF6C-4A7199C1C776\" targetRef=\"sid-FFCAA625-0092-4EBB-AF3A-6C79E85B5E1F\"></sequenceFlow>\n    <userTask id=\"sid-F6021017-4696-4115-9319-742B36597562\" activiti:assignee=\"${canditor}\" activiti:candidateUsers=\"${userId}\"></userTask>\n    <sequenceFlow id=\"sid-60EEF8F5-7FC0-4B2E-8C2A-D9DBA83889BB\" sourceRef=\"sid-FFCAA625-0092-4EBB-AF3A-6C79E85B5E1F\" targetRef=\"sid-F6021017-4696-4115-9319-742B36597562\"></sequenceFlow>\n  </process>\n  <bpmndi:BPMNDiagram id=\"BPMNDiagram_process\">\n    <bpmndi:BPMNPlane bpmnElement=\"process\" id=\"BPMNPlane_process\">\n      <bpmndi:BPMNShape bpmnElement=\"sid-0279BDBF-B082-4EAC-AF6C-4A7199C1C776\" id=\"BPMNShape_sid-0279BDBF-B082-4EAC-AF6C-4A7199C1C776\">\n        <omgdc:Bounds height=\"30.0\" width=\"30.0\" x=\"270.5\" y=\"182.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"sid-FFCAA625-0092-4EBB-AF3A-6C79E85B5E1F\" id=\"BPMNShape_sid-FFCAA625-0092-4EBB-AF3A-6C79E85B5E1F\">\n        <omgdc:Bounds height=\"80.0\" width=\"100.0\" x=\"345.5\" y=\"157.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape bpmnElement=\"sid-F6021017-4696-4115-9319-742B36597562\" id=\"BPMNShape_sid-F6021017-4696-4115-9319-742B36597562\">\n        <omgdc:Bounds height=\"80.0\" width=\"100.0\" x=\"480.0\" y=\"150.0\"></omgdc:Bounds>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNEdge bpmnElement=\"sid-B262BB35-838E-40F1-B573-C012579A9847\" id=\"BPMNEdge_sid-B262BB35-838E-40F1-B573-C012579A9847\">\n        <omgdi:waypoint x=\"300.5\" y=\"197.0\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"345.5\" y=\"197.0\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNEdge bpmnElement=\"sid-60EEF8F5-7FC0-4B2E-8C2A-D9DBA83889BB\" id=\"BPMNEdge_sid-60EEF8F5-7FC0-4B2E-8C2A-D9DBA83889BB\">\n        <omgdi:waypoint x=\"445.5\" y=\"194.39776951672863\"></omgdi:waypoint>\n        <omgdi:waypoint x=\"480.0\" y=\"192.60223048327137\"></omgdi:waypoint>\n      </bpmndi:BPMNEdge>\n    </bpmndi:BPMNPlane>\n  </bpmndi:BPMNDiagram>\n</definitions>',0),
('5002',1,'source',NULL,'{\"id\":\"canvas\",\"resourceId\":\"canvas\",\"stencilset\":{\"namespace\":\"http://b3mn.org/stencilset/bpmn2.0#\"}}',NULL),
('5004',1,'source',NULL,'{\"id\":\"canvas\",\"resourceId\":\"canvas\",\"stencilset\":{\"namespace\":\"http://b3mn.org/stencilset/bpmn2.0#\"}}',NULL),
('6',1,'123456.process.png','4','�PNG\r\n\Z\n\0\0\0\rIHDR\0\0N\0\0\0�\0\0\0��a\0\0\n�IDATx���MhU�p�̲�m�H�E7�X��EB]�@\Zn�̭���F�0�R�h6m�(��bK��P+�IL��\Zt쨣��8j�MԪ՚����#��Wc��ܜ����{�G�����N�\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0�\\I����ӳ�����}����65���ޞ<x�Z�j	\0)CӡC������޽{�\r�իW��\n!j�����+MBSj�ӽ���n]	\0)O�	-���J\0H�x��������J\0(��t�f!�9���l����\'�N\0 8���7��3kHN�Y���ߋ�	<���b˹-��.����4RW��x\'��Ŗs[�_%8uwn(\Z��c���0��rn����3m�E�S|�q�\0�[�m9�\'\0&p8�l���F�EW,8��S�	 ��ɖs��Yu�ps��sL\'��\'[��bu��tr������sL\'��\'[���U�G��{������d˹�T����G>{�.|/>�\nN\0�NvN	N�N�?�ۢ�s*Wp��l����p�#�d��}1R�g��Z}�\02�l9�ƻ�d�Ip�|p��\\p\Z��2=o��1�\0�|p��\\p\Z��74��c,8L��d˹��\0�l97\'���\rG[�\'�7\0x�p��\\p��\0/�������\0`��іs�Qp�\0�s8�rn8\nNz\0��p�z\0��p�z\0��p�Jp0��\'������d��ͻ֭[W����������\'�g�N�,Y�`�ڵ���{:::~*8)�I�\r ����V444ܨ��K�lْtuu%}}}���`ůɉ\'��۷\'��֬Y�\r6����\' 6n���U�V��X��QE����x�<y2Y�zu�۫�6m���\'%8S94}�p��w�~���E{��M����+�I	NJp��͛7��t��G�ϟO&�ŋ��|MMM����\'`�hhh�!�a�fi\"���������Je8���}����kzCo\0��`��oΟ?��D�4=k�)>}}��\'U\nñ�������r�����zCo\0<UWWw>^��:�k�jkk/\nN��cK��-7F*��+**����\02.����˗߿?y�V�\\9^o���>�i���iT��j,++���������4�r`2�>}:Y�l�%�I��p,�F��x\ZOoN@F���^�hѽW���ˊw��A��>TTT�������������������z�����?�	U9��{������X�d��[�>L&ю;��~���ad�\0���~$8��p|^�	�qQ��¯ׇ�0ԟ������I�K�n�\Zu3�g�����P	�����\"��nx���3g~#������X�l���1*�)�����(��iT���+P�c����ud�癡\'���1�uO1D=YAz\'��b؊�\'ڷ���EN�M���As���/\n�\Z��}�����S���@�(�1�����F�Cg�E��H�	Ȱ���{#�;Y�������(�1��w�����Θ1��b?�7\' #b(x�TRW�c��cEE����ʾ���������7��t�DV���r�w�j��h8\nNzȸ9s���5N555�R��&��p���c�|~�ѣG�\'{W]x�֔����̳�g1\rG�Io\0/zmnn�>��i۶m7B@Y���4f��p4\'��9�����ͻ1�w�?����9�p4���\0�����cǎݚ��t�������J�}2\rG��7\0o�^�paa2V�/^|%�^��\'%8������\\|��i׮]���~�uD������^�横���s�n����������!8}�T�#��p�z�\\.W�������?4��)>_x��R=Eg8\Z�zCo\0[y�EMMM�����D�����k���\'ԗ�{c8\Z�zCo\0<s婢������?~��[ZZNUVV��06���p�z�����������G�9�2��رc��7oޙ��J��&��p�z�eԬ�����\Z�tvv��J088x����)\n�C�:�~��C555�b`��L��{�p4���\0x%��!@Յ@�3To��\'���^�;~�J)��p4���\0�p4���\0�pT���\0\'%8)�	@pR���\0\'e8�\r���h8�\r���h8�\r���h8�\r%8�JpR���\'%8NJpRz@pR����\0�����\0�����\0��c*�۷�1IO\r��x�_,\0�I�48<x�000ฤ����ZBot�@pR)\rN?ۿ�������͛[i�������P��� 8�_������t<�&�65���n�	@pR.\0\0�I	N\0 8)�	\0�̖s[��q��ܖs\0`�l9��\0x	���r\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\00.��Ic��k\0\0\0\0IEND�B`�',1),
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
