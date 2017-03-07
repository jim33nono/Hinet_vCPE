<%@ page language='java' contentType='text/html; charset=UTF-8'
	pageEncoding='UTF-8'%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>


<form class='form-horizontal internetSettingForm' role="form" data-toggle="validator" action='back'>

	<div class='container'>
		<ul class='nav nav-pills'>
			<li><a href='#dhcpTab' data-toggle='tab'>DHCP 設定</a></li>
			<li class='active'><a href='#wanTab' data-toggle='tab'>WAN 設定</a></li>
			<li><a href='#ipsecTab' data-toggle='tab'>IPsec 設定</a></li>
			<li><a href='#lanTab' data-toggle='tab'>LAN 設定</a></li>
		</ul>

		<div class='tab-content clearfix'>
			
			<!-- DHCP -->
			<div class='tab-pane' id='dhcpTab'>
				<h3>請設定你的DHCP ${account}</h3>
				
				<div class='form-group'>
                    <div class='pull-left'>
                        <input class='btn btn-default btnBackPage' type='submit' value='返回'>
                    </div>
                </div>
				
				<div class='form-group'>
					<label for='defaultGatewayForDhcp' class='col-sm-2 control-label'>Default Gateway IP</label>
					<div class='col-sm-10'>
						<input type='text' class='form-control' id='defaultGatewayForDhcp' name='defaultGatewayForDhcp'
							value='${dhcpInfo.defaultGateway}'>
					</div>
				</div>
				<div class='form-group'>
					<label for='dhcpDns1' class='col-sm-2 control-label'>DNS1 IP</label>
					<div class='col-sm-10'>
						<input type='text' class='form-control' id='dhcpDns1'
							name='dhcpDns1' value='${dhcpInfo.dns1}'>
					</div>
				</div>
				<div class='form-group'>
					<label for='startIP' class='col-sm-2 control-label'>Range Start IP</label>
					<div class='col-sm-10'>
						<input type='text' class='form-control' id='startIP' name='startIP'
							value='${dhcpInfo.startIp}'>
					</div>
				</div>
				<div class='form-group'>
					<label for='endIP' class='col-sm-2 control-label'>Range End IP</label>
					<div class='col-sm-10'>
						<input type='text' class='form-control' id='endIP' name='endIP'
							value='${dhcpInfo.endIp}'>
					</div>
				</div>
				
				
				<div class='form-group'>
					<div class='col-sm-offset-11 col-sm-1'>
						<input class='btn btn-default btnSubmit' type='button' value='發送'>
					</div>
				</div>
				
			</div>
			
			<!-- WAN -->
			<div class='tab-pane active' id='wanTab'>
				<h3>請設定你的WAN ${account}</h3>
				
				<div class='form-group'>
					<div class='pull-left'>
						<input class='btn btn-default btnBackPage' type='submit' value='返回'>
					</div>
					<div class='pull-right'>
						<input class='btn btn-default btnCreateWan ' type='button' value='新增WAN'>
					</div>
				</div>
				<table id='tableWanList' class="table table-striped table-bordered" cellspacing="0" width="100%">
				
				</table>
				<br>
				
			</div>
			
			<!-- IPsec -->
			<div class='tab-pane' id='ipsecTab'>
				<h3>請設定你的IPsec ${account}</h3>
				
				<div class='form-group'>
					<div class='pull-left'>
						<input class='btn btn-default btnBackPage' type='submit' value='返回'>
					</div>
					<div class='pull-right'>
						<input class='btn btn-default btnCreateIpsec' type='button' value='新增IPsec'>
					</div>
				</div>
				
				<table id='tableIpsecList' class="table table-striped table-bordered" cellspacing="0" width="100%">
				
				</table>
				<br>
				
			</div>

			<!-- LAN -->
			<div class='tab-pane' id='lanTab'>
				<h3>請設定你的LAN ${account}</h3>
				
				<div class='form-group'>
                    <div class='pull-left'>
                        <input class='btn btn-default btnBackPage' type='submit' value='返回'>
                    </div>
                </div>
				
				<div class='form-group'>
					<label for='lanIp' class='col-sm-2 control-label'>LAN IP</label>
					<div class='col-sm-10'>
						<input type='text' class='form-control' id='lanIp'
							name='lanIp' value='${lanInfo.ip}' required>
					</div>
				</div>
				
				<div class='form-group'>
					<label for='lanSubnetMask' class='col-sm-2 control-label'>Subnet</label>
					<div class='col-sm-10'>
						<input type='text' class='form-control' id='lanSubnetMask'
							name='lanSubnetMask' value='${lanInfo.subnetMask}'>
					</div>
				</div>
				
				<!-- port id checkbox -->
				<c:forEach var="sortedPortIdVoList" items="${sortedPortIdVoList}">
					<c:choose>
						<c:when test="${sortedPortIdVoList.portIdValue == 0}">
							<div class='form-group'>
								<label for='lanPortMutiIdCheckbox${sortedPortIdVoList.index}' class='col-sm-2 control-label'>Port : ${sortedPortIdVoList.portName}</label>
								<div class='col-sm-10'>
									<input type='checkbox' class='form-control' id='lanPortMutiIdCheckbox${sortedPortIdVoList.index}'
										value='${sortedPortIdVoList.index}' >
								</div>
							</div>
						</c:when>
						<c:otherwise>
							<div class='form-group'>
								<label for='lanPortMutiIdCheckbox${sortedPortIdVoList.index}' class='col-sm-2 control-label'>Port : ${sortedPortIdVoList.portName}</label>
								<div class='col-sm-10'>
									<input type='checkbox' class='form-control' id='lanPortMutiIdCheckbox${sortedPortIdVoList.index}'
										 value='${sortedPortIdVoList.index}' checked>
								</div>
							</div>
						</c:otherwise>
					</c:choose>
					
				</c:forEach>
				
				<div class='form-group'>
					<div class='col-sm-offset-11 col-sm-1'>
						<input class='btn btn-default btnSubmit' type='button' value='發送'>
					</div>
				</div>
				
				
			</div>
			
		</div>
		
		
	</div>

</form>

<!-- including js -->
<script src='<c:url value='/resources/js/internetSetting.js' />'></script>
